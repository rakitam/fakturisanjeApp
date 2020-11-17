package sf.posinf.fakturisanje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sf.posinf.fakturisanje.mapstruct.FakturaMapper;
import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.Korisnik;
import sf.posinf.fakturisanje.services.interfaces.FakturaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.KorisnikServiceInterface;

@RestController
@RequestMapping(value = "api/korisnici")
@CrossOrigin("*")
public class KorisnikController {

	@Autowired
	private KorisnikServiceInterface korisnikServiceInterface;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	FakturaServiceInterface fakturaServiceInterface;
	
	@Autowired
	FakturaMapper fakturaMapper;

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Korisnik> getAll() {
		return this.korisnikServiceInterface.findAll();
	}

	//TODO: Dodati registraciju i login

	@GetMapping(value = "/{id}/fakture")
	public ResponseEntity getKorisnikFakture(@RequestParam(value = "id", defaultValue = "") long korisnikId) {
		List<Faktura> fakture = fakturaServiceInterface.findAllByKorisnik_Id(korisnikId);
		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(fakture));
		return ResponseEntity.ok().headers(headers).body(fakturaMapper.fakturaToDto(fakture));
	}

}
