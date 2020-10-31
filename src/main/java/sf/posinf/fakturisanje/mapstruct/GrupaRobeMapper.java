package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import sf.posinf.fakturisanje.dto.GrupaRobeDto;
import sf.posinf.fakturisanje.model.GrupaRobe;

@Mapper(componentModel = "spring") 
public interface GrupaRobeMapper {

	@Mapping(source = "preduzece.id", target = "preduzece")
	@Mapping(source = "pdv.id", target = "pdv")
	GrupaRobeDto grupaRobeToDto(GrupaRobe grupaRobe);
	@Mapping(source = "preduzece", target = "preduzece.id")
	@Mapping(source = "pdv", target = "pdv.id")
	GrupaRobe grupaRobeDtoToEntity(GrupaRobeDto grupaRobeDto);
}
