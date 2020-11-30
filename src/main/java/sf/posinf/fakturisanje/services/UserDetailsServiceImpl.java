package sf.posinf.fakturisanje.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sf.posinf.fakturisanje.model.Korisnik;
import sf.posinf.fakturisanje.repository.KorisnikRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    KorisnikRepository korisnikRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik k = korisnikRepository.findByEmail(username);
        if(k == null) {
            throw new UsernameNotFoundException(String.format("No user found under this email '%s'.", username));
        } else {
            return new User(k.getUsername(), k.getPassword(), k.getAuthorities());
        }
    }
}
