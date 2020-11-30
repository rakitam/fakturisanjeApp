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
import sf.posinf.fakturisanje.dto.GrupaRobeDto;
import sf.posinf.fakturisanje.mapstruct.GrupaRobeMapper;
import sf.posinf.fakturisanje.mapstruct.RobaUslugaMapper;
import sf.posinf.fakturisanje.model.GrupaRobe;
import sf.posinf.fakturisanje.model.PDV;
import sf.posinf.fakturisanje.services.impl.CenovnikService;
import sf.posinf.fakturisanje.services.interfaces.GrupaRobeServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.PDV_ServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.PreduzeceServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.RobaUslugaServiceInterface;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/grupe-robe")
@PreAuthorize("hasRole('ADMIN')")
public class GrupaRobeController {

	@Autowired
	private GrupaRobeServiceInterface grupaRobeServiceInterface;

	@Autowired
	private RobaUslugaServiceInterface robaUslugaServiceInterface;

	@Autowired
	private GrupaRobeMapper grupaRobeMapper;
	
	@Autowired
	RobaUslugaMapper robaUslugaMapper;

	@Autowired
	PDV_ServiceInterface pdvServiceInterface;

	@Autowired
	private PreduzeceServiceInterface preduzeceServiceInterface;

	@GetMapping
	public ResponseEntity getAll(@RequestParam(value = "naziv", defaultValue = "") String naziv, Pageable pageable) {
		Page<GrupaRobe> grupaRobePage = grupaRobeServiceInterface.findAll(naziv, pageable);
		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(grupaRobePage.getTotalPages()));
		return ResponseEntity.ok().headers(headers).body(grupaRobeMapper.grupaRobeToDto(grupaRobePage.getContent()));
	}

	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		GrupaRobe grupaRobe = grupaRobeServiceInterface.findOne(id);
		if (grupaRobe == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(grupaRobeMapper.grupaRobeToDto(grupaRobe));
	}

	@GetMapping("/{id}/roba")
	public ResponseEntity getRoba(@PathVariable("id") long id,
		  	@RequestParam(name = "naziv", defaultValue = "") String naziv,
	  		Pageable pageable) {
		return ResponseEntity.ok(robaUslugaMapper.robaUslugaToDto(
				robaUslugaServiceInterface.findAllByGrupaRobe_idAndNaziv(id, naziv, pageable).getContent().stream()
						.filter(p -> !p.getStavkeCenovnika().isEmpty()).collect(Collectors.toList())));
	}

	@PostMapping
	public ResponseEntity postGrupaRobe(@Validated @RequestBody GrupaRobeDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		GrupaRobe grupaRobe = grupaRobeMapper.grupaRobeDtoToEntity(dto);
		grupaRobe.setPdv(pdvServiceInterface.findOne(grupaRobe.getPdv().getId()));
		grupaRobe.setPreduzece(preduzeceServiceInterface.findOne(grupaRobe.getPreduzece().getId()));
		grupaRobe = grupaRobeServiceInterface.save(grupaRobe);
		if (grupaRobe == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(grupaRobeMapper.grupaRobeToDto(grupaRobe), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody GrupaRobeDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		GrupaRobe grupaRobe = grupaRobeMapper.grupaRobeDtoToEntity(dto);
		grupaRobe.setPdv(pdvServiceInterface.findOne(grupaRobe.getPdv().getId()));
		grupaRobe = grupaRobeServiceInterface.save(grupaRobe);
		if (grupaRobe == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(grupaRobeMapper.grupaRobeToDto(grupaRobe));
	}
}
