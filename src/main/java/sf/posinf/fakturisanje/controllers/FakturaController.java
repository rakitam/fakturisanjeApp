package sf.posinf.fakturisanje.controllers;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
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
import sf.posinf.fakturisanje.services.impl.PreduzeceService;
import sf.posinf.fakturisanje.services.interfaces.FakturaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.PoslovnaGodinaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.PreduzeceServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.StavkaFaktureServiceInterface;

import java.io.*;
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

	@Autowired
	private PreduzeceServiceInterface preduzeceServiceInterface;

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
		System.out.println(faktura.getDatumStorniranja());
		return ResponseEntity.ok(fakturaMapper.fakturaToDto(faktura));
	}

	//Izvestaj za jednu fakturu po njenom id
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}/napravi-izvestaj")
	public ResponseEntity napraviIzvestaj(@PathVariable("id") long id){

		Faktura faktura = fakturaServiceInterface.findOne(id);

		if(faktura==null){
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		List<StavkaFakture> stavkeFakture = stavkaFaktureServiceInterface.findByFaktura_id(faktura.getId());

		if(stavkeFakture==null || stavkeFakture.isEmpty()){
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		/* Convert the list above to JRBeanCollectionDataSource */
		JRBeanCollectionDataSource stavkeFaktureJasper = new JRBeanCollectionDataSource(stavkeFakture);

		/* Map to hold Jasper report Parameters */
		Map<String, Object> params  = new HashMap<>();

		params.put("faktura", faktura);
		params.put("stavkeFakture", stavkeFaktureJasper);

		try {

			/* Reading jrxml file and creating JasperDesign object */
			InputStream is = new FileInputStream(new File("C:\\Users\\Rakitica\\Documents\\FTN\\Poslovna Informatika\\fakturisanje\\src\\main\\resources\\Faktura.jrxml"));

			JasperDesign jasperDesign = JRXmlLoader.load(is);

			/* Compiling jrxml with the help of JasperReport class */
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

			/* Using jasperReport object to generate PDF */
			JasperPrint jp = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
			ByteArrayInputStream bais = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));
			JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\Rakitica\\Documents\\FTN\\Poslovna Informatika\\fakturisanje\\src\\main\\resources\\faktura.pdf");
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=" + faktura.getBrojFakture() + "-" + faktura.getPoslovnaGodina().getGodina() + ".pdf");
			return ResponseEntity
					.ok()
					.headers(headers)
					.contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(bais));
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//Izvestaj za sve fakture za poslovnu godinu
	//TODO: Popraviti - trenutno vraca samo jednu fakturu za poslovnu godinu
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{godina}/izvestaj")
	public ResponseEntity napraviIzvestajZaPoslovnuGodinu(@PathVariable("godina") int godina) throws JRException, FileNotFoundException {

		//Imamo samo jedno preduzece pod id-jem 1
		List<Faktura> fakture = fakturaServiceInterface.findAllByPreduzece_IdAndPoslovnaGodina_Godina(1, godina);

		if(fakture==null || fakture.isEmpty()){
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		/* Convert the list above to JRBeanCollectionDataSource */
		JRBeanCollectionDataSource faktureJasper = new JRBeanCollectionDataSource(fakture);

		/* Map to hold Jasper report Parameters */
		Map<String, Object> params  = new HashMap<String, Object>();

		params.put("godina", godina);
		params.put("fakture", faktureJasper);

		InputStream	is = new FileInputStream(new File("C:\\Users\\Rakitica\\Documents\\FTN\\Poslovna Informatika\\fakturisanje\\src\\main\\resources\\Fakture.jrxml"));

		JasperDesign jasperDesign = JRXmlLoader.load(is);

		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

		try {

			JasperPrint jp = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
			ByteArrayInputStream bais = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));
			JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\Rakitica\\Documents\\FTN\\Poslovna Informatika\\fakturisanje\\src\\main\\resources\\fakture.pdf");
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=" + "Izvestaj za poslovnu godinu" + "-" + godina +".pdf");
			return ResponseEntity
					.ok()
					.headers(headers)
					.contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(bais));
		} catch(Exception ex){
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
		//Datum fakture nikad nece biti manji od datuma valute, jer se prave u isto vreme
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
