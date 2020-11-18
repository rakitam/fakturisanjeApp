package sf.posinf.fakturisanje.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sf.posinf.fakturisanje.dto.StavkaFaktureDto;
import sf.posinf.fakturisanje.mapstruct.StavkaFaktureMapper;
import sf.posinf.fakturisanje.model.StavkaFakture;
import sf.posinf.fakturisanje.services.interfaces.StavkaFaktureServiceInterface;

@RestController
@RequestMapping("/api/stavkafakture")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class StavkaFaktureController {

	@Autowired
	StavkaFaktureServiceInterface stavkaFaktureService;

	@Autowired
	StavkaFaktureMapper stavkaFaktureMapper;

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
		StavkaFakture stavkaFakture = stavkaFaktureService.save(stavkaFaktureMapper.stavkaFaktureDtoToEntity(dto));
		if (stavkaFakture != null) {
			return new ResponseEntity(stavkaFaktureMapper.stavkaFaktureToDto(stavkaFakture), HttpStatus.CREATED);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	// Stavke fakture ne treba brisati niti menjati - ukoliko dodje do greske, faktura se stornira
}
