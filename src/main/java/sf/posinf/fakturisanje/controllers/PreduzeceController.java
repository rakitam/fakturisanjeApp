package sf.posinf.fakturisanje.controllers;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sf.posinf.fakturisanje.dto.PreduzeceDto;
import sf.posinf.fakturisanje.mapstruct.CenovnikMapper;
import sf.posinf.fakturisanje.mapstruct.FakturaMapper;
import sf.posinf.fakturisanje.mapstruct.GrupaRobeMapper;
import sf.posinf.fakturisanje.mapstruct.PreduzeceMapper;
import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.Preduzece;
import sf.posinf.fakturisanje.services.interfaces.GrupaRobeServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.MestoServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.PreduzeceServiceInterface;

import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/preduzeca")
public class PreduzeceController {

	@Autowired
	private PreduzeceServiceInterface preduzeceServiceInterface;

	@Autowired
	private GrupaRobeServiceInterface grupaRobeService;

	@Autowired
	private PreduzeceMapper preduzeceMapper;

	@Autowired
	private GrupaRobeMapper grupaRobeMapper;

	@Autowired
	private CenovnikMapper cenovnikMapper;

	@Autowired
	private FakturaMapper fakturaMapper;

	@Autowired
	private MestoServiceInterface mestoServiceInterface;

	@GetMapping
	public ResponseEntity getAll() {
		return ResponseEntity.ok(preduzeceMapper.preduzeceToDto(preduzeceServiceInterface.findAll()));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity getOne(@PathVariable long id) {
		Preduzece preduzece = preduzeceServiceInterface.findOne(id);
		if (preduzece == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(preduzeceMapper.preduzeceToDto(preduzece));
	}

	@GetMapping("/{id}/cenovnici")
	public ResponseEntity getCenovnici(@PathVariable("id") long id) {
		Preduzece preduzece = preduzeceServiceInterface.findOne(id);
		Set<Cenovnik> c = preduzece.getCenovnici();
		return ResponseEntity
				.ok(cenovnikMapper.cenovnikToDto(c.stream().collect(Collectors.toList())));
	}

	@GetMapping("/{id}/fakture")
	public ResponseEntity getFaktureIzlazne(@PathVariable("id") long id,
			@RequestParam(value = "godina", defaultValue = "0") int godina) {
			return ResponseEntity.ok(fakturaMapper.fakturaToDto(preduzeceServiceInterface.findAllByPreduzeceAndPoslovnaGodina(id, godina)));
	}

	@GetMapping("/{id}/grupe-robe")
	public ResponseEntity getGrupeRobe(@PathVariable("id") long id) {
		return ResponseEntity.ok(grupaRobeMapper.grupaRobeToDto(grupaRobeService.findByPreduzece_id(id)));
	}

	@PostMapping
	public ResponseEntity postPreduzece(@Validated @RequestBody PreduzeceDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		Preduzece preduzece = preduzeceMapper.preduzeceDtoToEntity(dto);
		preduzece.setMesto(mestoServiceInterface.findOne(preduzece.getMesto().getId()));
		preduzece = preduzeceServiceInterface.save(preduzece);
		if (preduzece == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(preduzeceMapper.preduzeceToDto(preduzece), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity putPreduzece(@PathVariable long id, @Validated @RequestBody PreduzeceDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		Preduzece preduzece = preduzeceMapper.preduzeceDtoToEntity(dto);
		preduzece.setMesto(mestoServiceInterface.findOne(preduzece.getMesto().getId()));
		preduzece = preduzeceServiceInterface.save(preduzece);
		if (preduzece == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(preduzeceMapper.preduzeceToDto(preduzece));
	}
}
