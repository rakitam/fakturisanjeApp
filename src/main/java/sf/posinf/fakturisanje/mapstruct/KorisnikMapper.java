package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import sf.posinf.fakturisanje.dto.KorisnikDto;
import sf.posinf.fakturisanje.model.Korisnik;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KorisnikMapper {

    KorisnikDto korisnikToDto(Korisnik korisnik);

    Korisnik korisnikDtoToEntity(KorisnikDto korisnikDto);

    List<Korisnik> korisnikDtoToEntity(List<KorisnikDto> korisnici);

    List<KorisnikDto> korisnikToDto(List<Korisnik> korisnici);
}
