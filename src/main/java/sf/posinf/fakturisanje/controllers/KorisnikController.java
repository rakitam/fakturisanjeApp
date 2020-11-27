package sf.posinf.fakturisanje.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value = "api/korisnici")
//@CrossOrigin("*")
public class KorisnikController {
/*

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

	// TODO: Dodati login

	@GetMapping(value = "/{id}/fakture")
	public ResponseEntity getKorisnikFakture(@RequestParam(value = "id", defaultValue = "") long korisnikId) {
		List<Faktura> fakture = fakturaServiceInterface.findAllByKorisnik_Id(korisnikId);
		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(fakture));
		return ResponseEntity.ok().headers(headers).body(fakturaMapper.fakturaToDto(fakture));
	}

*/
}
