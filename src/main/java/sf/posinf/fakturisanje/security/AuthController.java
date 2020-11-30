package sf.posinf.fakturisanje.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sf.posinf.fakturisanje.model.Korisnik;
import sf.posinf.fakturisanje.services.interfaces.KorisnikServiceInterface;


@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    TokenHelper tokenHelper;

    @Autowired
    private KorisnikServiceInterface korisnikServiceInterface;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto user) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            authenticationManager.authenticate(token);
            UserDetails details = userDetailsService.loadUserByUsername(user.getUsername());
            Korisnik korisnik = korisnikServiceInterface.findByEmail(user.getUsername());
            return ResponseEntity.ok(new LoginResponse(tokenHelper.generateToken(details), korisnik.getEmail(), korisnik.getUloga().getNaziv()));
        } catch (Exception e) {
            e.getStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


}
