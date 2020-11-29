package sf.posinf.fakturisanje.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sf.posinf.fakturisanje.dto.StopaPDV_Dto;
import sf.posinf.fakturisanje.mapstruct.StopaPDVMapper;
import sf.posinf.fakturisanje.model.StavkaFakture;
import sf.posinf.fakturisanje.model.StopaPDV;
import sf.posinf.fakturisanje.services.interfaces.PDV_ServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.RobaUslugaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.StopaPDV_ServiceInterface;

@RestController
@RequestMapping(value = "api/stope-pdv")
public class StopaPDV_Controller {

	@Autowired
	private StopaPDV_ServiceInterface stopaPdvService;

	@Autowired
	private StopaPDVMapper stopaPDVMapper;

	@Autowired
	private PDV_ServiceInterface pdvServiceInterface;

	@GetMapping
	public ResponseEntity getAll() {
		return ResponseEntity.ok(stopaPDVMapper.stopaPdvToDto(stopaPdvService.findAll()));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity getOne(@PathVariable long id) {
		StopaPDV stopa = stopaPdvService.findOne(id);
		if (stopa == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(stopaPDVMapper.stopaPdvToDto(stopa));
	}

	@PostMapping
	public ResponseEntity postStopaPDV(@Validated @RequestBody StopaPDV_Dto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		StopaPDV stopa = stopaPDVMapper.stopaPdvDtoToEntity(dto);
		stopa.setPdv(pdvServiceInterface.findOne(stopa.getPdv().getId()));
		stopa = stopaPdvService.save(stopa);
		if (stopa == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(stopaPDVMapper.stopaPdvToDto(stopa), HttpStatus.CREATED);
	}

}
