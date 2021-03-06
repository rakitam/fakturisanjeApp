package sf.posinf.fakturisanje.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sf.posinf.fakturisanje.dto.StavkaCenovnikaDTO;
import sf.posinf.fakturisanje.mapstruct.StavkaCenovnikaMapper;
import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.RobaUsluga;
import sf.posinf.fakturisanje.model.StavkaCenovnika;
import sf.posinf.fakturisanje.services.impl.StavkaFaktureService;
import sf.posinf.fakturisanje.services.interfaces.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/stavke-cenovnika")
public class StavkaCenovnikaController {

	@Autowired
	private StavkaCenovnikaServiceInterface stavkaCenovnikaService;

	@Autowired
	private StavkaCenovnikaMapper stavkaCenovnikaMapper;

	@Autowired
	private PDV_ServiceInterface pdv_serviceInterface;

	@Autowired
	private RobaUslugaServiceInterface robaUslugaServiceInterface;

	@Autowired
	StavkaFaktureServiceInterface stavkaFaktureServiceInterface;

	@GetMapping
	public ResponseEntity getAll(Pageable pageable) {
		Page<StavkaCenovnika> stavkeCenovnika = stavkaCenovnikaService.findAll(pageable);
		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(stavkeCenovnika.getTotalPages()));
		return ResponseEntity.ok().headers(headers).body(stavkaCenovnikaMapper.stavkaCenovnikaToDto(stavkeCenovnika.getContent()));
	}

	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity getOne(@PathVariable long id) {
		StavkaCenovnika stavka = stavkaCenovnikaService.findOne(id);
		if (stavka == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(stavkaCenovnikaMapper.stavkaCenovnikaToDto(stavka));
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity postStavkaCenovnika(@Validated @RequestBody StavkaCenovnikaDTO dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		StavkaCenovnika stavka = stavkaCenovnikaMapper.stavkaCenovnikaDtoToEntity(dto);
		RobaUsluga robaUsluga = robaUslugaServiceInterface.findOne(stavka.getRobaUsluga().getId());
		stavka.setCenaSaPdv(stavka.getCena() + (stavka.getCena() * pdv_serviceInterface.findActiveStopaPdv(robaUsluga.getGrupaRobe().getPdv().getId()).getProcenat()/100));
		stavka = stavkaCenovnikaService.save(stavka);
		if (stavka == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(stavkaCenovnikaMapper.stavkaCenovnikaToDto(stavka), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity putStavkaCenovnika(@PathVariable long id, @Validated @RequestBody StavkaCenovnikaDTO dto,
			Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		StavkaCenovnika stavka = stavkaCenovnikaMapper.stavkaCenovnikaDtoToEntity(dto);
		stavka = stavkaCenovnikaService.save(stavka);
		if (stavka == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(stavkaCenovnikaMapper.stavkaCenovnikaToDto(stavka));
	}

	//Principal - podaci o AUTENTIFIKOVANOM korisniku
	@PostMapping(value = "/{id}/add-to-korpa")
	public ResponseEntity addToKorpa(@PathVariable long id, @RequestParam(defaultValue = "1") int kolicina, @RequestParam(defaultValue = "0") int rabat, Principal principal) {
		StavkaCenovnika stavka = stavkaCenovnikaService.findOne(id);
		if (stavka == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		//Principal name = username
		stavkaFaktureServiceInterface.createSfFromSc(stavka, kolicina, rabat, principal.getName(), false);
		return ResponseEntity.ok(stavkaCenovnikaMapper.stavkaCenovnikaToDto(stavka));
	}

	@PostMapping(value = "/{id}/add-to-korpa/{korisnikEmail}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity addToKorpaByKorisnik(@PathVariable long id, @PathVariable("korisnikEmail") String korisnikEmail, @RequestParam(defaultValue = "1") int kolicina, @RequestParam(defaultValue = "0") int rabat) {
		StavkaCenovnika stavka = stavkaCenovnikaService.findOne(id);
		if (stavka == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		//Principal name = username
		stavkaFaktureServiceInterface.createSfFromSc(stavka, kolicina, rabat, korisnikEmail, true);
		return ResponseEntity.ok(stavkaCenovnikaMapper.stavkaCenovnikaToDto(stavka));
	}
}