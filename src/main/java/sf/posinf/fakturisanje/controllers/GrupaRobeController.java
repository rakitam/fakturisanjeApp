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

import sf.posinf.fakturisanje.dto.GrupaRobeDto;
import sf.posinf.fakturisanje.mapstruct.GrupaRobeMapper;
import sf.posinf.fakturisanje.mapstruct.RobaUslugaMapper;
import sf.posinf.fakturisanje.model.GrupaRobe;
import sf.posinf.fakturisanje.services.interfaces.GrupaRobeServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.RobaUslugaServiceInterface;

@RestController
@RequestMapping("api/grupa_robe")
public class GrupaRobeController {

	@Autowired
    private GrupaRobeServiceInterface grupaRobeServiceInterface;

    @Autowired
    private RobaUslugaServiceInterface robaUslugaService;
    
	@Autowired
	private GrupaRobeMapper grupaRobeMapper;
	
	@Autowired
	private RobaUslugaMapper robaUslugaMapper;
	
	@GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable("id") long id){
        GrupaRobe grupaRobe = grupaRobeServiceInterface.findOne(id);
        if(grupaRobe == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(grupaRobeMapper.grupaRobeToDto(grupaRobe));
    }
    
	
	// ********* ovo zavrsiti *************
	
	//@GetMapping("/{id}/roba")
   // public ResponseEntity getRoba(@PathVariable("id") long id){
   //     return ResponseEntity.ok(robaUslugaMapper.robaUslugaToDto(robaUslugaService.findAllByGrupaRobe_id(
    //            id,"",0,Integer.MAX_VALUE).getContent()
    //            .stream().filter(p -> !p.getStavkeCenovnika().isEmpty()).collect(Collectors.toList())));
    //}

	
    @PostMapping
    public ResponseEntity postGrupaRobe(@Validated @RequestBody GrupaRobeDto dto, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        GrupaRobe grupaRobe = grupaRobeServiceInterface.save(grupaRobeMapper.grupaRobeDtoToEntity(dto));
        if(grupaRobe==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(grupaRobeMapper.grupaRobeToDto(grupaRobe),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity putOne(@PathVariable("id") long id,@Validated @RequestBody GrupaRobeDto dto, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(dto.getId() !=id){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        GrupaRobe grupaRobe = grupaRobeServiceInterface.save(grupaRobeMapper.grupaRobeDtoToEntity(dto));
        if(grupaRobe==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(grupaRobeMapper.grupaRobeToDto(grupaRobe));
    }

	
}
