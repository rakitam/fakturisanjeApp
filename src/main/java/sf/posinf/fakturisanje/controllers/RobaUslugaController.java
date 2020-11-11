package sf.posinf.fakturisanje.controllers;

import java.util.Collections;
import java.util.List;

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

import sf.posinf.fakturisanje.dto.RobaUslugaDto;
import sf.posinf.fakturisanje.mapstruct.PDVMapper;
import sf.posinf.fakturisanje.mapstruct.RobaUslugaMapper;
import sf.posinf.fakturisanje.mapstruct.StavkaCenovnikaMapper;
import sf.posinf.fakturisanje.model.RobaUsluga;
import sf.posinf.fakturisanje.model.StavkaCenovnika;
import sf.posinf.fakturisanje.services.interfaces.RobaUslugaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.StavkaCenovnikaServiceInterface;

@RestController
@RequestMapping("/api/robausluga")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RobaUslugaController {

	@Autowired
	private RobaUslugaServiceInterface robaUslugaService;
	
	@Autowired
	private StavkaCenovnikaServiceInterface stavkaCenovnikaService;
	
	@Autowired
	private RobaUslugaMapper robaUslugaMapper;
	
	@Autowired
	private PDVMapper pdvMapper;
	
	@Autowired
	private StavkaCenovnikaMapper stavkaCenovnikaMapper;
	
	//TODO: Dodati get all metodu
	
	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		RobaUsluga robaUsluga = robaUslugaService.findOne(id);
		if (robaUsluga!=null) {
			return new ResponseEntity(robaUslugaMapper.robaUslugaToDto(robaUsluga),HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
    }
	
	@GetMapping("/{id}/cena")
	public ResponseEntity getCena(@PathVariable("id") long id) {
		RobaUsluga robaUsluga = robaUslugaService.findOne(id);
		if (robaUsluga!=null) {
			List<StavkaCenovnika> stavkeCenovnika = stavkaCenovnikaService.findAllByRoba_usluga_id(id);
			Collections.sort(stavkeCenovnika, (o1, o2) -> (o1.getCenovnik().getDatumVazenja().compareTo(o2.getCenovnik().getDatumVazenja())));
			return new ResponseEntity(stavkaCenovnikaMapper.stavkaCenovnikaToDto(stavkeCenovnika.get(stavkeCenovnika.size()-1)),HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
    }
	
	@GetMapping("/{id}/pdv")
	public ResponseEntity getPdv(@PathVariable("id") long id) {
		RobaUsluga robaUsluga = robaUslugaService.findOne(id);
		return ResponseEntity.ok(pdvMapper.pdvToDto(robaUsluga.getGrupaRobe().getPdv()));
	}
	
	@PostMapping
	public ResponseEntity postOne(@Validated @RequestBody RobaUslugaDto dto, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        RobaUsluga robaUsluga = robaUslugaService.save(robaUslugaMapper.robaUslugaDtoToentity(dto));
        if(robaUsluga != null){
        	return new ResponseEntity(robaUslugaMapper.robaUslugaToDto(robaUsluga),HttpStatus.CREATED);
        }else {
        	return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody RobaUslugaDto dto, Errors errors){
		
		if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        if(dto.getId()!=id){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        RobaUsluga robaUsluga = robaUslugaService.save(robaUslugaMapper.robaUslugaDtoToentity(dto));
        if(robaUsluga!=null){
            return new ResponseEntity(robaUslugaMapper.robaUslugaToDto(robaUsluga),HttpStatus.OK);
        }else {
        	return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
	}
	
	//TODO: Dodati delete metodu ovde i u klasama unazad
	
	
}
