package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sf.posinf.fakturisanje.dto.GrupaRobeDto;
import sf.posinf.fakturisanje.model.GrupaRobe;

import java.util.List;

@Mapper(componentModel = "spring", uses={PDVMapper.class})
public interface GrupaRobeMapper {

	@Mapping(source = "preduzece.id", target = "preduzece")
	GrupaRobeDto grupaRobeToDto(GrupaRobe grupaRobe);
	
	@Mapping(source = "preduzece", target = "preduzece.id")
	GrupaRobe grupaRobeDtoToEntity(GrupaRobeDto grupaRobeDto);
	
	List<GrupaRobe> grupaRobeDtoToEntity(List<GrupaRobeDto> grupeRobe);
	
	List<GrupaRobeDto> grupaRobeToDto(List<GrupaRobe> grupeRobe);
}
