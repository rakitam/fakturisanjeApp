package sf.posinf.fakturisanje.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sf.posinf.fakturisanje.dto.StopaPDV_Dto;
import sf.posinf.fakturisanje.mapstruct.StopaPDVMapper;
import sf.posinf.fakturisanje.model.StopaPDV;
import sf.posinf.fakturisanje.services.interfaces.StopaPDV_ServiceInterface;

@RestController
@RequestMapping(value = "api/stopa-pdv")
public class StopaPDV_Controller {

	@Autowired
	private StopaPDV_ServiceInterface stopaPdvService;

	@Autowired
	private StopaPDVMapper stopaPDVMapper;

	// dodati getAll

	
	@GetMapping(value = "/{id}")
	public ResponseEntity getOne(@PathVariable long id) {
		StopaPDV stopa = stopaPdvService.findOne(id);
		if (stopa == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(stopaPDVMapper.stopaPdvToDto(stopa));
	}

	
	@PostMapping
    public ResponseEntity postStopaPDV(@Validated @RequestBody StopaPDV_Dto dto, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        StopaPDV stopa = stopaPdvService.save(stopaPDVMapper.stopaPdvDtoToEntity(dto));
        if( stopa==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(stopaPDVMapper.stopaPdvToDto(stopa),HttpStatus.CREATED);
    }
	
	
    @PutMapping(value = "/{id}")
    public ResponseEntity putStopaPDV(@PathVariable long id, @Validated @RequestBody StopaPDV_Dto dto,Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        if(dto.getId()!=id){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        StopaPDV stopa = stopaPdvService.save(stopaPDVMapper.stopaPdvDtoToEntity(dto).setId(id));
        if(stopa==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(stopaPDVMapper.stopaPdvToDto(stopa));
    }
    

	
	
}
