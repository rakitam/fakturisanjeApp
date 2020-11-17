package sf.posinf.fakturisanje.controllers;

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

import sf.posinf.fakturisanje.dto.StavkaCenovnikaDTO;
import sf.posinf.fakturisanje.mapstruct.StavkaCenovnikaMapper;
import sf.posinf.fakturisanje.model.StavkaCenovnika;
import sf.posinf.fakturisanje.services.interfaces.StavkaCenovnikaServiceInterface;

@RestController
@RequestMapping("/api/stavka_cenovnika")
public class StavkaCenovnikaController {

    @Autowired
    private StavkaCenovnikaServiceInterface stavkaCenovnikaService;
    
    @Autowired
    private StavkaCenovnikaMapper stavkaCenovnikaMapper;
    
    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(stavkaCenovnikaMapper.stavkaCenovnikaToDto(stavkaCenovnikaService.findAll()));
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity getOne(@PathVariable long id){
    	StavkaCenovnika stavka = stavkaCenovnikaService.findOne(id);
        if(stavka==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(stavkaCenovnikaMapper.stavkaCenovnikaToDto(stavka));
    }
    
    @PostMapping
    public ResponseEntity postStavkaCenovnika(@Validated @RequestBody StavkaCenovnikaDTO dto, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        StavkaCenovnika stavka = stavkaCenovnikaService.save(stavkaCenovnikaMapper.stavkaCenovnikaDtoToEntity(dto));
        if(stavka==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(stavkaCenovnikaMapper.stavkaCenovnikaToDto(stavka),HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity putStavkaCenovnika(@PathVariable long id, @Validated @RequestBody StavkaCenovnikaDTO dto,Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        if(dto.getId()!=id){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        StavkaCenovnika stavka = stavkaCenovnikaService.save(stavkaCenovnikaMapper.stavkaCenovnikaDtoToEntity(dto).setId(id));
        if(stavka==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(stavkaCenovnikaMapper.stavkaCenovnikaToDto(stavka));
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteOne(@PathVariable long id){
        StavkaCenovnika stavka = stavkaCenovnikaService.findOne(id);
        if(stavka==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        stavkaCenovnikaService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}