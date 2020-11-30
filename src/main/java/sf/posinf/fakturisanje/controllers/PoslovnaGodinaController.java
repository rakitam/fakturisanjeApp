package sf.posinf.fakturisanje.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sf.posinf.fakturisanje.mapstruct.PoslovnaGodinaMapper;
import sf.posinf.fakturisanje.model.PoslovnaGodina;
import sf.posinf.fakturisanje.services.interfaces.PoslovnaGodinaServiceInterface;

@RestController
@RequestMapping(value = "api/poslovne-godine")
public class PoslovnaGodinaController {

    @Autowired
    private PoslovnaGodinaServiceInterface poslovnaGodinaServiceInterface;

    @Autowired
    private PoslovnaGodinaMapper poslovnaGodinaMapper;

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(poslovnaGodinaMapper.poslovnaGodinaToDto(poslovnaGodinaServiceInterface.findAll()));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity getOne(@PathVariable long id){
        PoslovnaGodina poslovnaGodina = poslovnaGodinaServiceInterface.findOne(id);
        if(poslovnaGodina==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(poslovnaGodinaMapper.poslovnaGodinaToDto(poslovnaGodina));
    }
}
