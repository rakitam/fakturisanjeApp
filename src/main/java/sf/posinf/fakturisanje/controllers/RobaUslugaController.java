package sf.posinf.fakturisanje.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sf.posinf.fakturisanje.dto.RobaUslugaDto;
import sf.posinf.fakturisanje.mapstruct.PDVMapper;
import sf.posinf.fakturisanje.mapstruct.RobaUslugaMapper;
import sf.posinf.fakturisanje.mapstruct.StavkaCenovnikaMapper;
import sf.posinf.fakturisanje.model.RobaUsluga;
import sf.posinf.fakturisanje.model.StavkaCenovnika;
import sf.posinf.fakturisanje.services.interfaces.GrupaRobeServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.RobaUslugaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.StavkaCenovnikaServiceInterface;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/robausluga")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RobaUslugaController {

	@Autowired
	private RobaUslugaServiceInterface robaUslugaService;

	@Autowired
	private StavkaCenovnikaServiceInterface stavkaCenovnikaServiceInterface;

	@Autowired
	private RobaUslugaMapper robaUslugaMapper;

	@Autowired
	private PDVMapper pdvMapper;

	@Autowired
	private StavkaCenovnikaMapper stavkaCenovnikaMapper;

	@Autowired
	private GrupaRobeServiceInterface grupaRobeServiceInterface;

	@GetMapping
	public ResponseEntity getAll(@RequestParam(value = "grupa", defaultValue = "0") Long grupa,
			@RequestParam(value = "naziv", defaultValue = "") String naziv, Pageable pageable) {
		if (grupa == 0) {
			Page<RobaUsluga> robeUsluge = robaUslugaService.findAll(naziv, pageable);
			HttpHeaders headers = new HttpHeaders();
			headers.set("total", String.valueOf(robeUsluge.getTotalPages()));
			return ResponseEntity.ok().headers(headers).body(robaUslugaMapper.robaUslugaToDto(robeUsluge.getContent()));
		} else {
			Page<RobaUsluga> robeUsluge = robaUslugaService.findAllByGrupaRobe_idAndNaziv(grupa, naziv, pageable);
			HttpHeaders headers = new HttpHeaders();
			headers.set("total", String.valueOf(robeUsluge.getTotalPages()));
			return ResponseEntity.ok().headers(headers).body(robaUslugaMapper.robaUslugaToDto(robeUsluge.getContent()));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		RobaUsluga robaUsluga = robaUslugaService.findOne(id);
		if (robaUsluga != null) {
			return new ResponseEntity(robaUslugaMapper.robaUslugaToDto(robaUsluga), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	//Za AKTUELNI cenovnik
	@GetMapping("/{id}/cena")
	public ResponseEntity getCena(@PathVariable("id") long id) {
		RobaUsluga robaUsluga = robaUslugaService.findOne(id);
		if (robaUsluga != null) {
			List<StavkaCenovnika> stavkeCenovnika = stavkaCenovnikaServiceInterface.findAllByRobaUsluga_Id(id);
			Collections.sort(stavkeCenovnika,
					(o1, o2) -> (o1.getCenovnik().getDatumVazenja().compareTo(o2.getCenovnik().getDatumVazenja())));
			return new ResponseEntity(
					stavkaCenovnikaMapper.stavkaCenovnikaToDto(stavkeCenovnika.get(stavkeCenovnika.size() - 1)),
					HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/pdv")
	public ResponseEntity getPdv(@PathVariable("id") long id) {
		RobaUsluga robaUsluga = robaUslugaService.findOne(id);
		return ResponseEntity.ok(pdvMapper.pdvToDto(robaUsluga.getGrupaRobe().getPdv()));
	}

	@PostMapping
	public ResponseEntity postOne(@Validated @RequestBody RobaUslugaDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		RobaUsluga robaUsluga = robaUslugaMapper.robaUslugaDtoToentity(dto);
		robaUsluga.setGrupaRobe(grupaRobeServiceInterface.findOne(robaUsluga.getGrupaRobe().getId()));
		robaUsluga = robaUslugaService.save(robaUsluga);
		if (robaUsluga != null) {
			return new ResponseEntity(robaUslugaMapper.robaUslugaToDto(robaUsluga), HttpStatus.CREATED);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody RobaUslugaDto dto,
			Errors errors) {

		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		RobaUsluga robaUsluga = robaUslugaMapper.robaUslugaDtoToentity(dto);
		robaUsluga.setGrupaRobe(grupaRobeServiceInterface.findOne(robaUsluga.getGrupaRobe().getId()));
		robaUsluga = robaUslugaService.save(robaUsluga);
		if (robaUsluga != null) {
			return new ResponseEntity(robaUslugaMapper.robaUslugaToDto(robaUsluga), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}
