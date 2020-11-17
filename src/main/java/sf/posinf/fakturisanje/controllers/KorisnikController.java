package sf.posinf.fakturisanje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sf.posinf.fakturisanje.model.Korisnik;
import sf.posinf.fakturisanje.services.interfaces.KorisnikServiceInterface;

@RestController
@RequestMapping(value = "api/korisnici")
@CrossOrigin("*")
public class KorisnikController {

	@Autowired
	private KorisnikServiceInterface korisnikServiceInterface;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Korisnik> getAll() {
		return this.korisnikServiceInterface.findAll();
	}
	
	//Dodati registraciju i login
}
