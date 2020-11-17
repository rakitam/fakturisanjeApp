package sf.posinf.fakturisanje.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import sf.posinf.fakturisanje.dto.PreduzeceDto;
import sf.posinf.fakturisanje.dto.RobaUslugaDto;
import sf.posinf.fakturisanje.model.Preduzece;
import sf.posinf.fakturisanje.model.RobaUsluga;

@Mapper(componentModel = "spring")
public interface RobaUslugaMapper {

	@Mapping(source = "grupaRobe.id", target = "grupaRobe")
	RobaUslugaDto robaUslugaToDto(RobaUsluga robaUsluga);
	
	@Mapping(source = "grupaRobe", target = "grupaRobe.id")
	RobaUsluga robaUslugaDtoToentity(RobaUslugaDto robaUslugaDto);
	
	List<RobaUsluga> robaUslugaDtoToEntity(List<RobaUslugaDto> robeUsluge);
	
	List<RobaUslugaDto> robaUslugaToDto(List<RobaUsluga> robeUsluge);
}
