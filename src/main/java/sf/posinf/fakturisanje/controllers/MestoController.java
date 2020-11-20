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
import sf.posinf.fakturisanje.dto.MestoDto;
import sf.posinf.fakturisanje.mapstruct.MestoMapper;
import sf.posinf.fakturisanje.model.Mesto;
import sf.posinf.fakturisanje.services.interfaces.MestoServiceInterface;

@RestController
@RequestMapping("api/mesta")
public class MestoController {

	@Autowired
	private MestoServiceInterface mestoServiceInterface;

	@Autowired
	private MestoMapper mestoMapper;

	@GetMapping
	public ResponseEntity getAll(@RequestParam(value = "naziv", defaultValue = "") String naziv, boolean obrisano,
			Pageable pageable) {
		Page<Mesto> mesta = mestoServiceInterface.findAll(naziv, false, pageable);
		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(mesta.getTotalPages()));
		return ResponseEntity.ok().headers(headers).body(mestoMapper.mestoToDto(mesta.getContent()));
	}

	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		Mesto mesto = mestoServiceInterface.findOne(id);
		if (mesto == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(mestoMapper.mestoToDto(mesto));
	}

	@PostMapping
	public ResponseEntity postMesto(@Validated @RequestBody MestoDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		Mesto mesto = mestoServiceInterface.save(mestoMapper.mestoDtoToEntity(dto));
		if (mesto == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(mestoMapper.mestoToDto(mesto));
	}

	@PutMapping("/{id}")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody MestoDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		Mesto mesto = mestoServiceInterface.save(mestoMapper.mestoDtoToEntity(dto));
		if (mesto == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(mestoMapper.mestoToDto(mesto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		Mesto mesto = mestoServiceInterface.findOne(id);
		if (mesto == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		mestoServiceInterface.delete(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
