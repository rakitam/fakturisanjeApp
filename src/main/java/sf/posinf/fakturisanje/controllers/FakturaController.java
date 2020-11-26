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
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sf.posinf.fakturisanje.dto.FakturaDto;
import sf.posinf.fakturisanje.mapstruct.FakturaMapper;
import sf.posinf.fakturisanje.mapstruct.RobaUslugaMapper;
import sf.posinf.fakturisanje.mapstruct.StavkaFaktureMapper;
import sf.posinf.fakturisanje.model.*;
import sf.posinf.fakturisanje.services.interfaces.FakturaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.PoslovnaGodinaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.StavkaFaktureServiceInterface;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
	private PoslovnaGodinaServiceInterface poslovnaGodinaServiceInterface;

	@Autowired
	private FakturaMapper fakturaMapper;

	@Autowired
	private StavkaFaktureMapper stavkaFaktureMapper;

	@Autowired
	private RobaUslugaMapper robaUslugaMapper;

	//TODO: Ne treba mi getAll jer imam samo izlazne
	@GetMapping
	public ResponseEntity getAll() {
		return ResponseEntity.ok(fakturaMapper.fakturaToDto(fakturaServiceInterface.findAll()));
	}

	/*@GetMapping(value = "/izlazne-fakture")
	public ResponseEntity getIzlazneFakture(@RequestParam(value = "godina", defaultValue = "0") int godina,
			@RequestParam(value = "preduzece", defaultValue = "") Long preduzece, Pageable pageable) {
		if(preduzece == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		Page<Faktura> fakture = fakturaServiceInterface.findAllByPoslovnaGodinaAndPreduzeceId(godina, preduzece,
				pageable);
		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(fakture.getTotalPages()));
		return ResponseEntity.ok().headers(headers).body(fakturaMapper.fakturaToDto(fakture.getContent()));
	}*/

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

	@PostMapping
	public ResponseEntity postFaktura(@Validated @RequestBody FakturaDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		PoslovnaGodina poslednjaPoslovnaGodina = poslovnaGodinaServiceInterface.findByZakljucanaIsFalse();
		Faktura faktura = fakturaMapper.fakturaDtoToEntity(dto);
		faktura.setBrojFakture(poslednjaPoslovnaGodina.getFakture().size() + 1);
		faktura.setStatusFakture(StatusFakture.PORUDZBENICA);
		faktura.setIznosZaPlacanje(0);
		faktura.setOsnovica(0);
		faktura.setUkupanPdv(0);
		faktura.setIznosBezRabata(0);
		faktura.setRabat(0);
		faktura.setPoslovnaGodina(poslednjaPoslovnaGodina);
		faktura = fakturaServiceInterface.save(faktura);
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
		faktura.setDatumFakture(new Date());
		faktura.setDatumValute(new Date());
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
		faktura.setStatusFakture(StatusFakture.FORMIRANA);
		faktura = fakturaServiceInterface.save(faktura);
		return new ResponseEntity(fakturaMapper.fakturaToDto(faktura), HttpStatus.OK);
	}

	@PutMapping("/{id}/storniraj")
	public ResponseEntity storniraj(@PathVariable("id") long id) {
		Faktura faktura = fakturaServiceInterface.findOne(id);
		if (faktura == null)
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		else if (faktura.getStatusFakture() == StatusFakture.STORNIRANA)
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		faktura.setStatusFakture(StatusFakture.STORNIRANA);
		faktura = fakturaServiceInterface.save(faktura);
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
