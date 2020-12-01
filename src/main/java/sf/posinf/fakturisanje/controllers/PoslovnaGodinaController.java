package sf.posinf.fakturisanje.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sf.posinf.fakturisanje.mapstruct.PoslovnaGodinaMapper;
import sf.posinf.fakturisanje.model.PoslovnaGodina;
import sf.posinf.fakturisanje.services.interfaces.PoslovnaGodinaServiceInterface;

@RestController
@RequestMapping(value = "api/poslovne-godine")
@PreAuthorize("hasRole('ADMIN')")
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
    
    @PutMapping("/{id}/zakljucaj")
    public ResponseEntity zakljucaj(@PathVariable("id") long id) {
        PoslovnaGodina pg = poslovnaGodinaServiceInterface.findOne(id);
        if (pg == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        else if (pg.isZakljucana())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        pg.setZakljucana(true);
        pg = poslovnaGodinaServiceInterface.save(pg);
        return new ResponseEntity(poslovnaGodinaMapper.poslovnaGodinaToDto(pg), HttpStatus.OK);
    }
}
