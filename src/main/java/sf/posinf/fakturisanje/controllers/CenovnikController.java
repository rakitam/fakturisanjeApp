package sf.posinf.fakturisanje.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sf.posinf.fakturisanje.dto.CenovnikDTO;
import sf.posinf.fakturisanje.mapstruct.CenovnikMapper;
import sf.posinf.fakturisanje.mapstruct.PreduzeceMapper;
import sf.posinf.fakturisanje.mapstruct.StavkaCenovnikaMapper;
import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.Preduzece;
import sf.posinf.fakturisanje.model.StavkaCenovnika;
import sf.posinf.fakturisanje.repository.PreduzeceRepository;
import sf.posinf.fakturisanje.services.interfaces.CenovnikServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.PreduzeceServiceInterface;

@RestController
@RequestMapping("/api/cenovnici")
@PreAuthorize("hasRole('ADMIN')")
@SuppressWarnings({"rawtypes", "unchecked"})
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

    @Autowired
    private PreduzeceServiceInterface preduzeceService;

    @GetMapping
    public ResponseEntity getAll(Pageable pageable) {
        Page<Cenovnik> cenovnici = cenovnikServiceInterface.findAll(pageable);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(cenovnici.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(cenovnikMapper.cenovnikToDto(cenovnici.getContent()));
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable("id") long id) {
        Cenovnik cenovnik = cenovnikServiceInterface.findOne(id);
        if (cenovnik != null) {
            return new ResponseEntity(cenovnikMapper.cenovnikToDto(cenovnik), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/stavke-cenovnika")
    public ResponseEntity getStavkeCenovnika(@PathVariable("id") long id,
                                             @RequestParam(value = "naziv", defaultValue = "") String naziv, Pageable pageable) {
        Cenovnik cenovnik = cenovnikServiceInterface.findOne(id);
        if (cenovnik != null) {
            Page<StavkaCenovnika> stavkeCenovnika = cenovnikServiceInterface.findAllByCenovnikId(cenovnik.getId(),
                    naziv, pageable);
            HttpHeaders headers = new HttpHeaders();
            headers.set("total", String.valueOf(stavkeCenovnika.getTotalPages()));
            return ResponseEntity.ok().headers(headers)
                    .body(stavkaCenovnikaMapper.stavkaCenovnikaToDto(stavkeCenovnika.getContent()));
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity postOne(@Validated @RequestBody CenovnikDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Cenovnik cenovnik = cenovnikMapper.cenovnikDtoToEntity(dto);
        cenovnik.setPreduzece(preduzeceService.findOne(cenovnik.getPreduzece().getId()));
        cenovnik = cenovnikServiceInterface.save(cenovnik);
        return new ResponseEntity(cenovnikMapper.cenovnikToDto(cenovnik), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody CenovnikDTO dto, Errors errors) {

        if (errors.hasErrors()) {
            return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        if (dto.getId() != id) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Cenovnik cenovnik = cenovnikServiceInterface.save(cenovnikMapper.cenovnikDtoToEntity(dto));
        if (cenovnik != null) {
            return new ResponseEntity(cenovnikMapper.cenovnikToDto(cenovnik), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
