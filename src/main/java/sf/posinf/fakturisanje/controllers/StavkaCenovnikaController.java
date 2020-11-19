package sf.posinf.fakturisanje.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sf.posinf.fakturisanje.dto.StavkaCenovnikaDTO;
import sf.posinf.fakturisanje.mapstruct.StavkaCenovnikaMapper;
import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.StavkaCenovnika;
import sf.posinf.fakturisanje.services.interfaces.CenovnikServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.GrupaRobeServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.RobaUslugaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.StavkaCenovnikaServiceInterface;

@RestController
@RequestMapping("/api/stavke-cenovnika")
public class StavkaCenovnikaController {

	@Autowired
	private StavkaCenovnikaServiceInterface stavkaCenovnikaService;

	@Autowired
	private StavkaCenovnikaMapper stavkaCenovnikaMapper;

	@Autowired
	private CenovnikServiceInterface cenovnikService;

	@Autowired
	private RobaUslugaServiceInterface robaUslugaServiceInterface;

	@GetMapping
	public ResponseEntity getAll() {
		return ResponseEntity.ok(stavkaCenovnikaMapper.stavkaCenovnikaToDto(stavkaCenovnikaService.findAll()));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity getOne(@PathVariable long id) {
		StavkaCenovnika stavka = stavkaCenovnikaService.findOne(id);
		if (stavka == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(stavkaCenovnikaMapper.stavkaCenovnikaToDto(stavka));
	}

	@PostMapping
	public ResponseEntity postStavkaCenovnika(@Validated @RequestBody StavkaCenovnikaDTO dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		StavkaCenovnika stavka = stavkaCenovnikaMapper.stavkaCenovnikaDtoToEntity(dto);
		stavka.setCenovnik(cenovnikService.findOne(stavka.getCenovnik().getId()));
		stavka.setRobaUsluga(robaUslugaServiceInterface.findOne(stavka.getRobaUsluga().getId()));
		stavka = stavkaCenovnikaService.save(stavka);
		if (stavka == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(stavkaCenovnikaMapper.stavkaCenovnikaToDto(stavka), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity putStavkaCenovnika(@PathVariable long id, @Validated @RequestBody StavkaCenovnikaDTO dto,
			Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		StavkaCenovnika stavka = stavkaCenovnikaMapper.stavkaCenovnikaDtoToEntity(dto);
		stavka.setCenovnik(cenovnikService.findOne(stavka.getCenovnik().getId()));
		stavka.setRobaUsluga(robaUslugaServiceInterface.findOne(stavka.getRobaUsluga().getId()));
		stavka = stavkaCenovnikaService.save(stavka);
		if (stavka == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(stavkaCenovnikaMapper.stavkaCenovnikaToDto(stavka));
	}
}