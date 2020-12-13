package sf.posinf.fakturisanje.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sf.posinf.fakturisanje.mapstruct.KorisnikMapper;
import sf.posinf.fakturisanje.model.Korisnik;
import sf.posinf.fakturisanje.services.interfaces.KorisnikServiceInterface;

import java.util.List;

@RestController
@RequestMapping("/api/korisnici")
@PreAuthorize("hasRole('ADMIN')")
public class KorisnikController {

    @Autowired
    private KorisnikServiceInterface korisnikServiceInterface;

    @Autowired
    private KorisnikMapper korisnikMapper;

    @GetMapping
    public ResponseEntity getAll() {
        List<Korisnik> korisnici = korisnikServiceInterface.findAll();
        return ResponseEntity.ok(korisnikMapper.korisnikToDto(korisnici));
    }

}
