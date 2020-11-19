package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sf.posinf.fakturisanje.dto.FakturaDto;
import sf.posinf.fakturisanje.model.Faktura;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FakturaMapper {

	@Mapping(source = "preduzece.id", target = "preduzece")
	@Mapping(source = "poslovnaGodina.id", target = "poslovnaGodina")
	FakturaDto fakturaToDto(Faktura faktura);
	
	@Mapping(source = "preduzece", target = "preduzece.id")
	@Mapping(source = "poslovnaGodina", target = "poslovnaGodina.id")
	Faktura fakturaDtoToEntity(FakturaDto fakturaDto);
	
	List<Faktura> fakturaDtoToEntity(List<FakturaDto> fakture);
	
	List<FakturaDto> fakturaToDto(List<Faktura> fakture);
	
}
