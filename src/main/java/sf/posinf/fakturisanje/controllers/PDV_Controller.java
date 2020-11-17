package sf.posinf.fakturisanje.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RestController;

import sf.posinf.fakturisanje.dto.PDV_Dto;
import sf.posinf.fakturisanje.mapstruct.PDVMapper;
import sf.posinf.fakturisanje.mapstruct.StopaPDVMapper;
import sf.posinf.fakturisanje.model.PDV;
import sf.posinf.fakturisanje.model.StopaPDV;
import sf.posinf.fakturisanje.services.interfaces.PDV_ServiceInterface;

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
    public ResponseEntity getAll(){
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
		PDV pdv = pdvServiceInterface.findOne(id);
		if (pdv == null || pdv.getStopePdv().isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		List<StopaPDV> stope = new ArrayList(pdv.getStopePdv());
		
		//Sortiramo stopePDV-a po datumu vazenja
		Collections.sort(stope, (stopaPdv1, stopaPdv2) -> (stopaPdv1.getDatumVazenja().compareTo(stopaPdv2.getDatumVazenja())));
	
		//vracamo trenutnu stopu, samo nam je ta potrebna
		return ResponseEntity.ok(stopaPDVMapper.stopaPdvToDto(stope.get(stope.size() - 1)));
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
