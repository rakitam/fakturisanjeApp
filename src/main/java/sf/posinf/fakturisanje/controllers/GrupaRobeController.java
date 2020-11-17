package sf.posinf.fakturisanje.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
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
	private RobaUslugaServiceInterface robaUslugaServiceInterface;

	@Autowired
	private GrupaRobeMapper grupaRobeMapper;

	@Autowired
	private RobaUslugaMapper robaUslugaMapper;
	
	@GetMapping
    public ResponseEntity getAll(@RequestParam(value = "page",defaultValue = "0") int page,
                                 @RequestParam(value = "num",defaultValue = Integer.MAX_VALUE+"") int num,
                                 @RequestParam(value = "naziv",defaultValue = "") String naziv){
        Page<GrupaRobe> grupaRobePage = grupaRobeServiceInterface.findAll(naziv,page,num);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total",String.valueOf(grupaRobePage.getTotalPages()));
        return ResponseEntity.ok()
                .headers(headers)
                .body(grupaRobeMapper.grupaRobeToDto(grupaRobePage.getContent()));
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
	public ResponseEntity getRoba(@PathVariable("id") long id) {
		return ResponseEntity.ok(robaUslugaMapper
				.robaUslugaToDto(robaUslugaServiceInterface.findAllByGrupaRobe_id(id, "", 0, Integer.MAX_VALUE).getContent()
						.stream().filter(p -> !p.getStavkeCenovnika().isEmpty()).collect(Collectors.toList())));
	}

	@PostMapping
	public ResponseEntity postGrupaRobe(@Validated @RequestBody GrupaRobeDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		GrupaRobe grupaRobe = grupaRobeServiceInterface.save(grupaRobeMapper.grupaRobeDtoToEntity(dto));
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
		GrupaRobe grupaRobe = grupaRobeServiceInterface.save(grupaRobeMapper.grupaRobeDtoToEntity(dto));
		if (grupaRobe == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(grupaRobeMapper.grupaRobeToDto(grupaRobe));
	}

}
