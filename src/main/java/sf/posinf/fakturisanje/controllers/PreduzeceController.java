package sf.posinf.fakturisanje.controllers;

import java.awt.PageAttributes.MediaType;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;
import sf.posinf.fakturisanje.dto.PreduzeceDto;
import sf.posinf.fakturisanje.mapstruct.CenovnikMapper;
import sf.posinf.fakturisanje.mapstruct.FakturaMapper;
import sf.posinf.fakturisanje.mapstruct.GrupaRobeMapper;
import sf.posinf.fakturisanje.mapstruct.PreduzeceMapper;
import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.PoslovniPartner;
import sf.posinf.fakturisanje.model.Preduzece;
import sf.posinf.fakturisanje.services.interfaces.GrupaRobeServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.PoslovniPartnerServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.PreduzeceServiceInterface;



@RestController
@RequestMapping("/api/preduzece")
public class PreduzeceController {

    @Autowired
    private PoslovniPartnerServiceInterface poslovniPartnerServiceInterface;


    @Autowired
    private PreduzeceServiceInterface preduzeceService;

    @Autowired
    private GrupaRobeServiceInterface grupaRobeService;
    
    @Autowired
    private PreduzeceMapper preduzeceMapper;

    @Autowired
    private PoslovniPartner poslovniPartner;

    @Autowired
    private FakturaMapper fakturaMapper;
    
    @Autowired
    private GrupaRobeMapper grupaRobeMapper;
    
    @Autowired
    private CenovnikMapper cenovnikMapper;

//    @GetMapping
//    public ResponseEntity getAll(){
//        return ResponseEntity.ok(toDto.convert(preduzeceService.findAll()));
//    }
//    
    
    @GetMapping(value = "/{id}")
    public ResponseEntity getOne(@PathVariable long id){
    	Preduzece preduzece = preduzeceService.findOne(id);
        if(preduzece==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(preduzeceMapper.preduzeceToDto(preduzece));
    }

    @GetMapping("/{id}/partneri")
    public ResponseEntity getPartneri(@PathVariable("id") long id){
        return ResponseEntity.ok(poslovniPartnerServiceInterface.findOne(id));
    }


//    @GetMapping("/{id}/cenovnici")
//    public ResponseEntity getCenovnici(@PathVariable("id") long id){
//    	Preduzece preduzece = preduzeceService.findOne(id);
//    	Set<Cenovnik> c = preduzece.getCenovnici();
//		return ResponseEntity.ok(toCenovnikDto.convert(c.stream().filter(x -> !x.isObrisano()).collect(Collectors.toList())));
//    }
    

//    @GetMapping("/{id}/fakture/ulazne")
//    public ResponseEntity getFakture(@PathVariable("id") long id,
//                                     @RequestParam(value = "godina", defaultValue = "0") int godina){
//        if(godina==0){
//            return ResponseEntity.ok(toFakturaDto.convert(preduzeceService.findAllByPreduzeceAndVrstaFakture(true,id)));
//        }
//        return ResponseEntity.ok(toFakturaDto.convert(preduzeceService.findAllByPreduzeceAndVrstaFakture(true,id)
//                .stream().filter(f -> f.getPoslovnaGodina().getId()==godina).collect(Collectors.toList())));
//    }
    
//    @GetMapping("/{id}/fakture/izlazne")
//    public ResponseEntity getFaktureIzlazne(@PathVariable("id") long id,
//                                            @RequestParam(value = "godina", defaultValue = "0") int godina){
//        if(godina==0){
//            return ResponseEntity.ok(toFakturaDto.convert(preduzeceService.findAllByPreduzeceAndVrstaFakture(false,id)));
//        }
//        return ResponseEntity.ok(toFakturaDto.convert(preduzeceService.findAllByPreduzeceAndVrstaFakture(false,id)
//                .stream().filter(f -> f.getPoslovnaGodina().getId()==godina).collect(Collectors.toList())));
//    }
    
    @GetMapping("/{id}/grupe_robe")
    public ResponseEntity getGrupeRobe(@PathVariable("id") long id){
        return ResponseEntity.ok(preduzeceService.findOne(id));
    }

    @PostMapping
    public ResponseEntity postPreduzece(@Validated @RequestBody PreduzeceDto dto, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        Preduzece preduzece = preduzeceService.save(preduzeceMapper.preduzeceDtoToEntity(dto));
        if(preduzece==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(preduzeceMapper.preduzeceToDto(preduzece),HttpStatus.CREATED);
    }
    
    
    @PutMapping(value = "/{id}")
    public ResponseEntity putPreduzece(@PathVariable long id, @Validated @RequestBody PreduzeceDto dto,Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        if(dto.getId()!=id){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Preduzece preduzece = preduzeceService.save(preduzeceMapper.preduzeceDtoToEntity(dto));
        if(preduzece==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(preduzeceMapper.preduzeceToDto(preduzece));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteOne(@PathVariable long id){
        Preduzece preduzece = preduzeceService.findOne(id);
        if(preduzece==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        preduzeceService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    
//    @GetMapping("{id}/reports/ulazne")
//    public ResponseEntity getReportsUlazne(@RequestParam("godina") int godina, @PathVariable("id") long id){
//    	Preduzece preduzece = preduzeceService.findOne(id);
//    
//        List<Faktura> fakture = preduzeceService.findAllByPreduzeceAndVrstaFaktureAndPlaceno(true, id, true);
//        List<Faktura> faktureFinal = new ArrayList<Faktura>();
//        if(fakture==null){
//        	return new ResponseEntity(HttpStatus.NOT_FOUND);
//        	}
//    	
//        if(fakture.isEmpty()){
//        	return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        
//        Map<String, Object> params  = new HashMap<String, Object>();
//        for(Faktura i : fakture){
//        	if(i.getPoslovnaGodina().getGodina() == godina){
//            	faktureFinal.add(i);      		
//        	}        	
//        }
//        params.put("godina", godina);
//        params.put("fakture", faktureFinal);
//        params.put("preduzece", preduzece);      		
//        		
//        		try{
//        		String type = "ulazne-fakture";  	
//    		JasperPrint jp = JasperFillManager.fillReport(this.getClass().getResource("/"+type+".jasper").openStream(), params, new JREmptyDataSource());
//    		ByteArrayInputStream bis = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));
//    		HttpHeaders headers = new HttpHeaders();
//    		headers.add("Content-Disposition", "inline; filename="+preduzece.getNaziv()+"-"+"ulazne-fakture"+".pdf");
//    		return ResponseEntity
//    	       		.ok()
//    	       		.headers(headers)
//    	       		.contentType(MediaType.APPLICATION_PDF)
//    	       		.body(new InputStreamResource(bis));
//        }catch(Exception ex){
//        	ex.printStackTrace();
//
//        }
//    	return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//    
//    @GetMapping("{id}/reports/izlazne")
//    public ResponseEntity getReportsIzlazne(@RequestParam("godina") int godina, @PathVariable("id") long id){
//    	Preduzece preduzece = preduzeceService.findOne(id);
//        List<Faktura> fakture = preduzeceService.findAllByPreduzeceAndVrstaFaktureAndPlaceno(false, id, true);
//        List<Faktura> faktureFinal = new ArrayList<Faktura>();
//        if(fakture==null){
//        	return new ResponseEntity(HttpStatus.NOT_FOUND);
//        	}
//        if(fakture.isEmpty()){
//        	return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        
//        
//        Map<String, Object> params  = new HashMap<String, Object>();
//        for(Faktura i : fakture){
//        	if(i.getPoslovnaGodina().getGodina() == godina){
//            	faktureFinal.add(i);      		
//        	}        	
//        }
//        params.put("fakture", faktureFinal);
//        try{
//        
//    		String type = "izlazne-fakture";  	
//    		JasperPrint jp = JasperFillManager.fillReport(this.getClass().getResource("/"+type+".jasper").openStream(), params, new JREmptyDataSource());
//    		ByteArrayInputStream bis = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));
//    		HttpHeaders headers = new HttpHeaders();
//    		headers.add("Content-Disposition", "inline; filename="+preduzece.getNaziv()+"-"+"izlazne-fakture"+".pdf");
//    		return ResponseEntity
//    	       		.ok()
//    	       		.headers(headers)
//    	       		.contentType(MediaType.APPLICATION_PDF)
//    	       		.body(new InputStreamResource(bis));
//        }catch(Exception ex){
//        	ex.printStackTrace();
//
//        }
//    	return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
	        
}
