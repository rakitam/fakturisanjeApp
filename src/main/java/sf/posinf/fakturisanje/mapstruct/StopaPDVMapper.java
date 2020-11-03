package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import sf.posinf.fakturisanje.dto.StopaPDV_Dto;
import sf.posinf.fakturisanje.model.StopaPDV;

@Mapper(componentModel = "spring")
public interface StopaPDVMapper {

	@Mapping(source = "pdv.id", target = "pdv")
	StopaPDV_Dto stopaPdvToDto(StopaPDV stopaPdv);
	StopaPDV stopaPdvDtoToEntity(StopaPDV_Dto stopaPdv_Dto);
}
