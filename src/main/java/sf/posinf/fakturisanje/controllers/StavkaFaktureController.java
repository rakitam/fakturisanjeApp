package sf.posinf.fakturisanje.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sf.posinf.fakturisanje.dto.StavkaFaktureDto;
import sf.posinf.fakturisanje.mapstruct.StavkaFaktureMapper;
import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.StavkaFakture;
import sf.posinf.fakturisanje.services.interfaces.FakturaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.GrupaRobeServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.RobaUslugaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.StavkaFaktureServiceInterface;

@RestController
@RequestMapping("/api/stavke-fakture")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class StavkaFaktureController {

	@Autowired
	StavkaFaktureServiceInterface stavkaFaktureService;

	@Autowired
	StavkaFaktureMapper stavkaFaktureMapper;

	@Autowired
	private FakturaServiceInterface fakturaService;

	@Autowired
	private RobaUslugaServiceInterface robaUslugaServiceInterface;

	@GetMapping
	public ResponseEntity getAll() {
		return new ResponseEntity(stavkaFaktureMapper.stavkaFaktureToDto(stavkaFaktureService.findAll()),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		StavkaFakture stavkaFakture = stavkaFaktureService.findOne(id);
		if (stavkaFakture != null) {
			return new ResponseEntity(stavkaFaktureMapper.stavkaFaktureToDto(stavkaFakture), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity postOne(@Validated @RequestBody StavkaFaktureDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		StavkaFakture stavkaFakture = stavkaFaktureMapper.stavkaFaktureDtoToEntity(dto);
		stavkaFakture.setFaktura(fakturaService.findOne(stavkaFakture.getFaktura().getId()));
		stavkaFakture.setRobaUsluga(robaUslugaServiceInterface.findOne(stavkaFakture.getRobaUsluga().getId()));
		stavkaFakture = stavkaFaktureService.save(stavkaFakture);
		if (stavkaFakture != null) {
			return new ResponseEntity(stavkaFaktureMapper.stavkaFaktureToDto(stavkaFakture), HttpStatus.CREATED);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	// Stavke fakture ne treba brisati niti menjati - ukoliko dodje do greske, faktura se stornira
}
