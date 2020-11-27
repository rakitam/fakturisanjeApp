package sf.posinf.fakturisanje.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sf.posinf.fakturisanje.dto.CenovnikDTO;
import sf.posinf.fakturisanje.dto.StavkaFaktureDto;
import sf.posinf.fakturisanje.mapstruct.StavkaFaktureMapper;
import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.Mesto;
import sf.posinf.fakturisanje.model.StavkaFakture;
import sf.posinf.fakturisanje.repository.StavkaFaktureRepository;
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
	StavkaFaktureRepository stavkaFaktureRepository;

	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		StavkaFakture stavkaFakture = stavkaFaktureService.findOne(id);
		if (stavkaFakture != null) {
			return new ResponseEntity(stavkaFaktureMapper.stavkaFaktureToDto(stavkaFakture), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	//TODO: Testirati
	@PutMapping("/{id}")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody StavkaFaktureDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		StavkaFakture sf = stavkaFaktureService.save(stavkaFaktureMapper.stavkaFaktureDtoToEntity(dto));
		if (sf != null) {
			return new ResponseEntity(stavkaFaktureMapper.stavkaFaktureToDto(sf), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	//TODO: Testirati
	@DeleteMapping("/{id}")
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		stavkaFaktureService.delete(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
