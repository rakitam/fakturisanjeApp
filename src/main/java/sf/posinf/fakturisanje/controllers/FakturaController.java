package sf.posinf.fakturisanje.controllers;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sf.posinf.fakturisanje.dto.FakturaDto;
import sf.posinf.fakturisanje.mapstruct.FakturaMapper;
import sf.posinf.fakturisanje.mapstruct.RobaUslugaMapper;
import sf.posinf.fakturisanje.mapstruct.StavkaFaktureMapper;
import sf.posinf.fakturisanje.model.*;
import sf.posinf.fakturisanje.services.impl.KorisnikService;
import sf.posinf.fakturisanje.services.interfaces.FakturaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.PoslovnaGodinaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.StavkaFaktureServiceInterface;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

@RestController
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping("api/fakture")
public class FakturaController {

	@Autowired
	private StavkaFaktureServiceInterface stavkaFaktureServiceInterface;

	@Autowired
	private FakturaServiceInterface fakturaServiceInterface;

	@Autowired
	private FakturaMapper fakturaMapper;

	@Autowired
	private StavkaFaktureMapper stavkaFaktureMapper;

	@Autowired
	private KorisnikService korisnikService;

	//U zavisnosti od uloge korisnika, vratice ili sve, ili fakture samo jednog korisnika
	@GetMapping
	public ResponseEntity getAll(@RequestParam(name = "status", defaultValue = "") String status, Pageable pageable, Principal principal) {
		Korisnik k = korisnikService.findByEmail(principal.getName());
		if(k.getUloga().getNaziv().equals("ROLE_ADMIN")) {
			Page<Faktura> fakture = fakturaServiceInterface.findAll(status, pageable);
			HttpHeaders headers = new HttpHeaders();
			headers.set("total", String.valueOf(fakture.getTotalPages()));
			return ResponseEntity.ok().headers(headers).body(fakturaMapper.fakturaToDto(fakture.getContent()));
		} else {
			Page<Faktura> fakture = fakturaServiceInterface.findAllByKorisnik(k.getId(), status, pageable);
			HttpHeaders headers = new HttpHeaders();
			headers.set("total", String.valueOf(fakture.getTotalPages()));
			return ResponseEntity.ok().headers(headers).body(fakturaMapper.fakturaToDto(fakture.getContent()));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		Faktura faktura = fakturaServiceInterface.findOne(id);
		if (faktura == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(fakturaMapper.fakturaToDto(faktura));
	}

	//TODO: Skontati jasper
	@GetMapping("/{id}/report")
	public ResponseEntity getReport(@PathVariable("id") long id) {
		Faktura faktura = fakturaServiceInterface.findOne(id);
		if (faktura == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("faktura", faktura);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connectionURL = "jdbc:mysql://localhost:3306/fakturisanjeBaza";

			Properties appProperties = new Properties();
			try (InputStream is = this.getClass().getResource("/application.properties").openStream()) {
				appProperties.load(is);
			}

			Properties properties = new Properties();
			properties.setProperty("user", appProperties.getProperty("spring.datasource.username"));
			properties.setProperty("password", appProperties.getProperty("spring.datasource.password"));
			properties.setProperty("useSSL", "false");
			Connection conn = DriverManager.getConnection(connectionURL, properties);
			params.put("connectionInfo", conn);
			String type = "izlazna-faktura";
			JasperPrint jp = JasperFillManager.fillReport(
					this.getClass().getResource("/" + type + ".jasper").openStream(), params, new JREmptyDataSource());
			ByteArrayInputStream bis = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=" + faktura.getBrojFakture() + "-"
					+ faktura.getPoslovnaGodina().getGodina() + ".pdf");
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(bis));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/{id}/stavke")
	public ResponseEntity getStavke(@PathVariable("id") long id) {
		return ResponseEntity
				.ok(stavkaFaktureMapper.stavkaFaktureToDto(stavkaFaktureServiceInterface.findByFaktura_id(id)));
	}

	@GetMapping("/active")
	public ResponseEntity getActiveKorpa(Principal principal) {
		Korisnik k = korisnikService.findByEmail(principal.getName());
		Faktura faktura = fakturaServiceInterface.getActiveFakturaForKorisnik(k);
		if (faktura == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(fakturaMapper.fakturaToDto(faktura), HttpStatus.CREATED);
	}

	@PutMapping("/{id}/plati")
	public ResponseEntity platiFakturu(@PathVariable("id") long id) {
		Faktura faktura = fakturaServiceInterface.findOne(id);
		if (faktura == null)
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		else if (faktura.getStatusFakture() != StatusFakture.FORMIRANA)
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		faktura.setStatusFakture(StatusFakture.PLACENA);
		faktura = fakturaServiceInterface.save(faktura);
		return new ResponseEntity(fakturaMapper.fakturaToDto(faktura), HttpStatus.OK);
	}

	@PutMapping("/{id}/formiraj")
	public ResponseEntity formirajFakturu(@PathVariable("id") long id) {
		Faktura faktura = fakturaServiceInterface.findOne(id);
		if (faktura == null)
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		else if (faktura.getStatusFakture() != StatusFakture.PORUDZBENICA)
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		//TODO: Pogledati ogranicenje - datum valute ne sme biti manji od datuma fakture
		Date datumFakture = new Date();
		Date datumValute = new Date();
		if(datumValute.before(datumFakture)) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		faktura.setDatumFakture(datumFakture);
		faktura.setDatumValute(datumValute);
		faktura.setStatusFakture(StatusFakture.FORMIRANA);
		faktura = fakturaServiceInterface.save(faktura);
		return new ResponseEntity(fakturaMapper.fakturaToDto(faktura), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}/storniraj")
	public ResponseEntity storniraj(@PathVariable("id") long id) {
		Faktura faktura = fakturaServiceInterface.findOne(id);
		if (faktura == null)
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		else if (faktura.getStatusFakture() == StatusFakture.STORNIRANA)
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		fakturaServiceInterface.storniraj(faktura);
		return new ResponseEntity(fakturaMapper.fakturaToDto(faktura), HttpStatus.OK);
	}

	//Faktura se moze menjati iskljucivo pre nego sto je formirana
	@PutMapping("/{id}")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody FakturaDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		Faktura fakturaDB = fakturaServiceInterface.findOne(id);
		if(fakturaDB.getStatusFakture() != StatusFakture.PORUDZBENICA) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		Faktura faktura = fakturaServiceInterface.save(fakturaMapper.fakturaDtoToEntity(dto));
		if (faktura == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(fakturaMapper.fakturaToDto(faktura));
	}
}
