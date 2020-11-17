package sf.posinf.fakturisanje.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;

import sf.posinf.fakturisanje.dto.CenovnikDTO;
import sf.posinf.fakturisanje.dto.PreduzeceDto;
import sf.posinf.fakturisanje.mapstruct.CenovnikMapper;
import sf.posinf.fakturisanje.mapstruct.PreduzeceMapper;
import sf.posinf.fakturisanje.mapstruct.StavkaCenovnikaMapper;
import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.Preduzece;
import sf.posinf.fakturisanje.model.StavkaCenovnika;
import sf.posinf.fakturisanje.repository.PreduzeceRepository;
import sf.posinf.fakturisanje.services.interfaces.CenovnikServiceInterface;

@RestController
@RequestMapping("/api/cenovnik")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CenovnikController {
	
	@Autowired
	PreduzeceRepository preduzeceRepository;
	
	@Autowired
	CenovnikServiceInterface cenovnikServiceInterface;
	
	@Autowired
	CenovnikMapper cenovnikMapper;
	
	@Autowired
	StavkaCenovnikaMapper stavkaCenovnikaMapper;
	
	@Autowired
	PreduzeceMapper preduzeceMapper;
	
	@GetMapping
	public ResponseEntity getAll(Pageable pageable) {
		Page<Cenovnik> cenovnici = cenovnikServiceInterface.findAll(pageable);
		HttpHeaders headers = new HttpHeaders();
		headers.set("total",String.valueOf(cenovnici.getTotalPages()));
		return ResponseEntity.ok()
				.headers(headers)
				.body(cenovnikMapper.cenovnikToDto(cenovnici.getContent()));
    }
	
	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		Cenovnik cenovnik = cenovnikServiceInterface.findOne(id);
		if (cenovnik!=null) {
			return new ResponseEntity(cenovnikMapper.cenovnikToDto(cenovnik),HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
    }
	
	@GetMapping("/{id}/stavke-cenovnika")
	public ResponseEntity getStavkeCenovnika(@PathVariable("id") long id, @RequestParam(value = "naziv", defaultValue = "") String naziv, Pageable pageable) {
		Cenovnik cenovnik = cenovnikServiceInterface.findOne(id);
		if (cenovnik!=null) {
			Page<StavkaCenovnika> stavkeCenovnika = cenovnikServiceInterface.findAllByCenovnikId(cenovnik.getId(),naziv, pageable);
			HttpHeaders headers = new HttpHeaders();
			headers.set("total",String.valueOf(stavkeCenovnika.getTotalPages()));
			return ResponseEntity.ok()
					.headers(headers)
					.body(stavkaCenovnikaMapper.stavkaCenovnikaToDto
							 (stavkeCenovnika.getContent()));
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity postOne(@Validated @RequestBody CenovnikDTO dto, @RequestParam("preduzece") boolean preduzece, @RequestParam("id") long id,Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        
        Cenovnik cenovnik = cenovnikServiceInterface.save(cenovnikMapper.cenovnikDtoToEntity(dto));        
        if(cenovnik != null){
        	if(preduzece == true){
  	        		Preduzece pred = preduzeceRepository.findById(id).orElse(null);
  	        		pred.getCenovnici().add(cenovnik);	  	        		
  	        		preduzeceRepository.save(pred);
        	}
        	return new ResponseEntity(cenovnikMapper.cenovnikToDto(cenovnik),HttpStatus.CREATED);
        }else {
        	return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteOne(@PathVariable("id") long id){
		Cenovnik cenovnik = cenovnikServiceInterface.findOne(id);
		if (cenovnik!=null) {
			cenovnikServiceInterface.delete(cenovnik.getId());
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
        Cenovnik cenovnik = cenovnikServiceInterface.save(cenovnikMapper.cenovnikDtoToEntity(dto));
        if(cenovnik!=null){
            return new ResponseEntity(cenovnikMapper.cenovnikToDto(cenovnik),HttpStatus.OK);
        }else {
        	return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
	}
}
