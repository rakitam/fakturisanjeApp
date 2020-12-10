package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sf.posinf.fakturisanje.dto.FakturaDto;
import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.Korisnik;
import sf.posinf.fakturisanje.model.PoslovnaGodina;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PoslovnaGodina.class})
public interface FakturaMapper {

	@Mapping(source = "preduzece.id", target = "preduzece")
	FakturaDto fakturaToDto(Faktura faktura);
	
	@Mapping(source = "preduzece", target = "preduzece.id")
	Faktura fakturaDtoToEntity(FakturaDto fakturaDto);
	
	List<Faktura> fakturaDtoToEntity(List<FakturaDto> fakture);
	
	List<FakturaDto> fakturaToDto(List<Faktura> fakture);
	
}
