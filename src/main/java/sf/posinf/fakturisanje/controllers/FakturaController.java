package sf.posinf.fakturisanje.controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import sf.posinf.fakturisanje.dto.FakturaDto;
import sf.posinf.fakturisanje.mapstruct.FakturaMapper;
import sf.posinf.fakturisanje.mapstruct.RobaUslugaMapper;
import sf.posinf.fakturisanje.mapstruct.StavkaFaktureMapper;
import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.PoslovnaGodina;
import sf.posinf.fakturisanje.model.Preduzece;
import sf.posinf.fakturisanje.model.RobaUsluga;
import sf.posinf.fakturisanje.model.StavkaCenovnika;
import sf.posinf.fakturisanje.services.impl.PreduzeceService;
import sf.posinf.fakturisanje.services.interfaces.FakturaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.PoslovnaGodinaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.StavkaFaktureServiceInterface;

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

	@Autowired
	private PreduzeceService preduzeceService;

	@GetMapping
	public ResponseEntity getAll() {
		return ResponseEntity.ok(fakturaMapper.fakturaToDto(fakturaServiceInterface.findAll()));
	}

	/*
	 * TODO: Trebaju mi sve izlazne fakture za moje preduzece
	 * 
	 * @GetMapping(value = "/izlazne-fakture") public ResponseEntity
	 * getIzlazneFakture(@RequestParam(value = "godina", defaultValue = "0") int
	 * godina,
	 * 
	 * @RequestParam(value = "naziv", defaultValue = "") String naziv) { if (godina
	 * == 0) { List<Faktura> fakture =
	 * fakturaServiceInterface.findAllByVrstaFaktureAndPoslovniPartner(false,
	 * naziv); HttpHeaders headers = new HttpHeaders(); headers.set("total",
	 * String.valueOf(fakture)); return
	 * ResponseEntity.ok().headers(headers).body(fakturaMapper.fakturaToDto(fakture)
	 * ); } List<Faktura> fakture = fakturaServiceInterface.
	 * findAllByVrstaFaktureAndPoslovniPartnerAndPoslovnaGodina(false, naziv,
	 * godina); HttpHeaders headers = new HttpHeaders(); headers.set("total",
	 * String.valueOf(fakture)); return
	 * ResponseEntity.ok().headers(headers).body(fakturaMapper.fakturaToDto(fakture)
	 * ); }
	 */

	/*
	 * Da li ova metoda treba ovde ili kod korisnika; da li je dobar mapping value?
	 * 
	 * @GetMapping(value = "/korisnik-fakture/{id}") public ResponseEntity
	 * getKorisnikFakture(@RequestParam(value = "id", defaultValue = "") String id)
	 * { if (godina == 0) { List<Faktura> fakture =
	 * fakturaServiceInterface.findAllByKorisnik(id); HttpHeaders headers = new
	 * HttpHeaders(); headers.set("total", String.valueOf(fakture)); return
	 * ResponseEntity.ok().headers(headers).body(fakturaMapper.fakturaToDto(fakture)
	 * ); } List<Faktura> fakture =
	 * fakturaServiceInterface.findAllByKorisnik_Email(); HttpHeaders headers = new
	 * HttpHeaders(); headers.set("total", String.valueOf(fakture)); return
	 * ResponseEntity.ok().headers(headers).body(fakturaMapper.fakturaToDto(fakture)
	 * ); }
	 */

	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		Faktura faktura = fakturaServiceInterface.findOne(id);
		if (faktura == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(fakturaMapper.fakturaToDto(faktura));
	}

	@GetMapping("/{id}/report")
	public ResponseEntity getReport(@PathVariable("id") long id) {
		Faktura faktura = fakturaServiceInterface.findOne(id);
		if (faktura == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("faktura", faktura);
		try {
			Class.forName("com.mysql.jdbc.Driver");
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

	@GetMapping("/{id}/robaCenovnika")

	public ResponseEntity getCenovnikRoba(@PathVariable("id") long id) {
		Faktura faktura = fakturaServiceInterface.findOne(id);
		if (faktura == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		List<Cenovnik> cenovnici = new ArrayList<Cenovnik>();
		cenovnici.addAll(faktura.getPreduzece().getCenovnici());
		Cenovnik c = cenovnici.get(cenovnici.size() - 1);
		ArrayList<RobaUsluga> roba = new ArrayList();
		for (StavkaCenovnika s : c.getStavkeCenovnika()) {
			roba.add(s.getRobaUsluga());
		}
		return ResponseEntity.ok(robaUslugaMapper.robaUslugaToDto(roba));
	}

	/*
	 * TODO: Dodati status fakture
	 * 
	 * @PostMapping public ResponseEntity postFaktura(@Validated @RequestBody
	 * FakturaDto dto, Errors errors) { if (errors.hasErrors()) { return new
	 * ResponseEntity(HttpStatus.BAD_REQUEST); } PoslovnaGodina
	 * poslednjaPoslovnaGodina =
	 * poslovnaGodinaServiceInterface.findByZakljucanaIsFalse(); Faktura faktura =
	 * fakturaMapper.fakturaDtoToEntity(dto);
	 * faktura.setBrojFakture(poslednjaPoslovnaGodina.getFakture().size() + 1)
	 * //.setStatusFakture(faktura.setStatusFakture(null)
	 * .setIznosZaPlacanje(0).setOsnovica(0)
	 * .setUkupanPdv(0).setIznosBezRabata(0).setRabat(0).setPlaceno(false)
	 * .setPoslovnaGodina(poslednjaPoslovnaGodina).setObrisano(false); faktura =
	 * fakturaServiceInterface.save(faktura); if (faktura == null) { return new
	 * ResponseEntity(HttpStatus.BAD_REQUEST); } return new
	 * ResponseEntity(fakturaMapper.fakturaToDto(faktura), HttpStatus.CREATED); }
	 */

	/*TODO: Popraviti jer ce status fakture biti u enumu
	 * @PutMapping("/{id}/plati")
	 *
	public ResponseEntity platiFakturu(@PathVariable("id") long id) {
		Faktura faktura = fakturaServiceInterface.findOne(id);
		if (faktura == null)
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		else if (faktura.isPlaceno())
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		faktura.setDatumFakture(new Date()).setDatumValute(new Date()).setPlaceno(true);
		faktura = fakturaServiceInterface.save(faktura);
		return new ResponseEntity(fakturaMapper.fakturaToDto(faktura), HttpStatus.OK);
	}*/

	@PutMapping("/{id}")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody FakturaDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		Faktura faktura = fakturaServiceInterface.save(fakturaMapper.fakturaDtoToEntity(dto));
		if (faktura == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(fakturaMapper.fakturaToDto(faktura));
	}

	// TODO: Popraviti metodu tako da radi u zavisnosti od statusa fakture
	@DeleteMapping("/{id}")
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		Faktura faktura = fakturaServiceInterface.findOne(id);
		if (faktura == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		fakturaServiceInterface.delete(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}