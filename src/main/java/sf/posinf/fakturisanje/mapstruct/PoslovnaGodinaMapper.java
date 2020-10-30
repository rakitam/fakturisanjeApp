package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;

import sf.posinf.fakturisanje.dto.PoslovnaGodinaDto;
import sf.posinf.fakturisanje.model.PoslovnaGodina;

@Mapper(componentModel = "spring") 
public interface PoslovnaGodinaMapper {

	PoslovnaGodinaDto poslovnaGodinaToDto(PoslovnaGodina poslovnaGodina);
	PoslovnaGodina poslovnaGodinaDtoToEntity(PoslovnaGodinaDto poslovnaGodina);
}
