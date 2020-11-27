package sf.posinf.fakturisanje.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sf.posinf.fakturisanje.dto.PDV_Dto;
import sf.posinf.fakturisanje.mapstruct.PDVMapper;
import sf.posinf.fakturisanje.mapstruct.StopaPDVMapper;
import sf.posinf.fakturisanje.model.PDV;
import sf.posinf.fakturisanje.model.StopaPDV;
import sf.posinf.fakturisanje.services.interfaces.PDV_ServiceInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "api/pdv")
public class PDV_Controller {

	@Autowired
	private PDV_ServiceInterface pdvServiceInterface;

	@Autowired
	private PDVMapper pdvMapper;

	@Autowired
	private StopaPDVMapper stopaPDVMapper;

	@GetMapping
	public ResponseEntity getAll() {
		return ResponseEntity.ok(pdvMapper.pdvToDto(pdvServiceInterface.findAll()));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity getOne(@PathVariable long id) {
		PDV pdv = pdvServiceInterface.findOne(id);
		if (pdv == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(pdvMapper.pdvToDto(pdv));
	}

	@GetMapping(value = "/{id}/stopa")
	public ResponseEntity getStopa(@PathVariable long id) {
		StopaPDV stopaPdv = pdvServiceInterface.findActiveStopaPdv(id);
		return ResponseEntity.ok(stopaPDVMapper.stopaPdvToDto(stopaPdv));
	}

	@PostMapping()
	public ResponseEntity postPdv(@Validated @RequestBody PDV_Dto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		PDV pdv = pdvServiceInterface.save(pdvMapper.pdvDtoToEntity(dto));
		if (pdv == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(pdvMapper.pdvToDto(pdv));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity editPdv(@PathVariable long id, @Validated @RequestBody PDV_Dto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		PDV pdv = pdvServiceInterface.save(pdvMapper.pdvDtoToEntity(dto));
		if (pdv == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(pdvMapper.pdvToDto(pdv));
	}
}
