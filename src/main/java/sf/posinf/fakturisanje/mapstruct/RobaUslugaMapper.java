package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sf.posinf.fakturisanje.dto.RobaUslugaDto;
import sf.posinf.fakturisanje.model.RobaUsluga;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GrupaRobeMapper.class})
public interface RobaUslugaMapper {

	RobaUslugaDto robaUslugaToDto(RobaUsluga robaUsluga);
	
	RobaUsluga robaUslugaDtoToentity(RobaUslugaDto robaUslugaDto);
	
	List<RobaUsluga> robaUslugaDtoToEntity(List<RobaUslugaDto> robeUsluge);
	
	List<RobaUslugaDto> robaUslugaToDto(List<RobaUsluga> robeUsluge);
}
