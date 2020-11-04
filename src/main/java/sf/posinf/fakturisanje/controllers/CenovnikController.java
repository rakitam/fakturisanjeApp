package sf.posinf.fakturisanje.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;

import sf.posinf.fakturisanje.dto.CenovnikDTO;
import sf.posinf.fakturisanje.mapstruct.CenovnikMapper;
import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.PoslovniPartner;
import sf.posinf.fakturisanje.model.Preduzece;
import sf.posinf.fakturisanje.repository.PoslovniPartnerRepository;
import sf.posinf.fakturisanje.repository.PreduzeceRepository;
import sf.posinf.fakturisanje.services.interfaces.CenovnikServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.PoslovniPartnerServiceInterface;

@RestController
@RequestMapping("/api/cenovnik")
public class CenovnikController {
	
	@Autowired
	PreduzeceRepository preduzeceRepository;
	
	@Autowired
	PoslovniPartnerRepository poslovniPartnerRepository;
	
	@Autowired
	PoslovniPartnerServiceInterface poslovniPartnerService;
	
	@Autowired
	CenovnikServiceInterface cenovnikService;
	
	@Autowired
	CenovnikMapper cenovnikMapper; 
	
	
	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		Cenovnik cenovnik = cenovnikService.findOne(id);
		if (cenovnik!=null) {
			return new ResponseEntity(cenovnikMapper.cenovnikToDto(cenovnik),HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
    }
	
	@PostMapping
	public ResponseEntity postOne(@Validated @RequestBody CenovnikDTO dto, @RequestParam("preduzece") boolean preduzece, @RequestParam("id") long id,Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        
        Cenovnik cenovnik = cenovnikService.save(cenovnikMapper.cenovnikDtoToEntity(dto));        
        if(cenovnik != null){
        	if(preduzece == true){
  	        		Preduzece pred = preduzeceRepository.findById(id).orElse(null);
  	        		pred.getCenovnici().add(cenovnik);	  	        		
  	        		preduzeceRepository.save(pred);
        	}
    	        else{
    	        	PoslovniPartner partner = poslovniPartnerRepository.findById(id).orElse(null);
    	        	partner.getCenovnici().add(cenovnik);
    	        	poslovniPartnerRepository.save(partner);    	        
    	        }
        	return new ResponseEntity(cenovnikMapper.cenovnikToDto(cenovnik),HttpStatus.CREATED);
        }else {
        	return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteOne(@PathVariable("id") long id){
		Cenovnik cenovnik = cenovnikService.findOne(id);
		if (cenovnik!=null) {
			cenovnikService.delete(cenovnik.getId());
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody CenovnikDTO dto, Errors errors){
		
		if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        if(dto.getId()!=id){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Cenovnik cenovnik = cenovnikService.save(cenovnikMapper.cenovnikDtoToEntity(dto));
        if(cenovnik!=null){
            return new ResponseEntity(cenovnikMapper.cenovnikToDto(cenovnik),HttpStatus.OK);
        }else {
        	return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
	}
}
