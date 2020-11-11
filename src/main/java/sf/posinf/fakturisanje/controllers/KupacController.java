package sf.posinf.fakturisanje.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import sf.posinf.fakturisanje.dto.PoslovniPartnerDto;
import sf.posinf.fakturisanje.mapstruct.CenovnikMapper;
import sf.posinf.fakturisanje.mapstruct.PoslovniPartnerMapper;
import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.Kupac;
import sf.posinf.fakturisanje.services.interfaces.KupacServiceInterface;

@RestController
@RequestMapping(value = "api/poslovni_partneri")
public class KupacController {

    @Autowired
    private KupacServiceInterface poslovniPartnerServiceInterface;

    @Autowired
    private PoslovniPartnerMapper poslovniPartnerMapper;
    
    @Autowired
    private CenovnikMapper cenovnikMapper;

    /*@GetMapping
    public ResponseEntity getAll(@RequestParam(value = "page",defaultValue = "0") int page,
                                 @RequestParam(value = "num",defaultValue = Integer.MAX_VALUE+"") int num,
                                 @RequestParam(value = "filter",defaultValue = "") String filter,
                                 @RequestParam(value = "tip", defaultValue = "3") int tip){
        List<PoslovniPartner> poslovniPartneri = poslovniPartnerServiceInterface.findAll(filter);
        tip = Integer.valueOf(tip);
        if(tip==3){
            return ResponseEntity.ok(toDto.convert(poslovniPartneri));
        }
        // ne moze drugacije
        int finalTip = tip;
        List<PoslovniPartner> poslovniPartnerFilter = poslovniPartneri.stream()
                .filter(p->p.getVrstaPartnera()== finalTip && !p.isObrisano()).collect(Collectors.toList());

        PageRequest pageRequest = new PageRequest(page,num);
        int start = (int) pageRequest.getOffset();
        int end = (start + pageRequest.getPageSize()) > poslovniPartnerFilter.size() ? poslovniPartnerFilter.size() : (start + num);
        Page<PoslovniPartner> poslovniPartnerPage = new PageImpl<>(poslovniPartnerFilter.subList(start, end),
                pageRequest, poslovniPartnerFilter.size());

        HttpHeaders headers = new HttpHeaders();
        headers.set("total",String.valueOf(poslovniPartnerPage.getTotalPages()));
        return ResponseEntity.ok()
                .headers(headers)
                .body(toDto.convert(poslovniPartnerPage.getContent()));
    }*/
    
    /* Smara optional
    @GetMapping(value = "/{id}")
    public ResponseEntity getOne(@PathVariable long id){
        Kupac partner = poslovniPartnerServiceInterface.findOne(id);
        if(partner==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(poslovniPartnerMapper.poslovniPartnerToDto(partner));
    }*/
    
    /*@GetMapping("/cenovnici")
    public ResponseEntity getCenovnici(){
    	ArrayList<Cenovnik> c = new ArrayList<Cenovnik>();
    	for (PoslovniPartner p : poslovniPartnerServiceInterface.findAll())
    		c.addAll(p.getCenovnici());
    	return ResponseEntity.ok(cenovnikMapper.cenovnikToDto((c.stream().filter(x -> !x.isObrisano()).collect(Collectors.toList())));
    }
    

    @GetMapping("/{id}/cenovnici")
    public ResponseEntity getCenovnici(@PathVariable("id") long id){
    	PoslovniPartner p = poslovniPartnerServiceInterface.findOne(id);
    	Set<Cenovnik> c = p.getCenovnici();
    	return ResponseEntity.ok(cenovnikMapper.cenovnikToDto(cenovnik)(c.stream().filter(x -> !x.isObrisano()).collect(Collectors.toList())));
    }*/
    
    
    @PostMapping
    public ResponseEntity postPartner(@Validated @RequestBody PoslovniPartnerDto dto, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        Kupac partner = poslovniPartnerServiceInterface.save(poslovniPartnerMapper.poslovniPartnerDtotoEntity(dto));
        if(partner==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(poslovniPartnerMapper.poslovniPartnerToDto(partner),HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity putPartner(@PathVariable long id, @Validated @RequestBody PoslovniPartnerDto dto,Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        if(dto.getId()!=id){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Kupac partner = poslovniPartnerServiceInterface.save(poslovniPartnerMapper.poslovniPartnerDtotoEntity(dto));
        if(partner==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(poslovniPartnerMapper.poslovniPartnerToDto(partner));
    }
    
    /* Smara optional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteOne(@PathVariable long id){
        Kupac partner = poslovniPartnerServiceInterface.findOne(id);
        if(partner==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        poslovniPartnerServiceInterface.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }*/

}
