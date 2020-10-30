package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;

import sf.posinf.fakturisanje.dto.PDV_Dto;
import sf.posinf.fakturisanje.model.PDV;

@Mapper(componentModel = "spring")
public interface PDVMapper {

	PDV_Dto pdvToDto(PDV pdv);
	PDV pdvDtoToEntity(PDV_Dto pdvDto);
}
