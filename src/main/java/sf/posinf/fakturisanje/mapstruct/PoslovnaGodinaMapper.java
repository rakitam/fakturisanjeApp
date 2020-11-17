package sf.posinf.fakturisanje.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;

import sf.posinf.fakturisanje.dto.PDV_Dto;
import sf.posinf.fakturisanje.dto.PoslovnaGodinaDto;
import sf.posinf.fakturisanje.model.PDV;
import sf.posinf.fakturisanje.model.PoslovnaGodina;

@Mapper(componentModel = "spring") 
public interface PoslovnaGodinaMapper {

	PoslovnaGodinaDto poslovnaGodinaToDto(PoslovnaGodina poslovnaGodina);
	
	PoslovnaGodina poslovnaGodinaDtoToEntity(PoslovnaGodinaDto poslovnaGodina);
	
	List<PoslovnaGodina> poslovnaGodinaDtoToEntity(List<PoslovnaGodinaDto> poslovneGodine);
	
	List<PoslovnaGodinaDto> poslovnaGodinaToDto(List<PoslovnaGodina> poslovneGodine);
}
