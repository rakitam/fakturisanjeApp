package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sf.posinf.fakturisanje.dto.StavkaCenovnikaDTO;
import sf.posinf.fakturisanje.model.StavkaCenovnika;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RobaUslugaMapper.class, CenovnikMapper.class})
public interface StavkaCenovnikaMapper {

	StavkaCenovnikaDTO stavkaCenovnikaToDto(StavkaCenovnika stavkaCenovnika);

	StavkaCenovnika stavkaCenovnikaDtoToEntity(StavkaCenovnikaDTO stavkaCenovnikaDTO);
	
	List<StavkaCenovnika> stavkaCenovnikaDtoToEntity(List<StavkaCenovnikaDTO> stavkeCenovnika);
	
	List<StavkaCenovnikaDTO> stavkaCenovnikaToDto(List<StavkaCenovnika> stavkeCenovnika);
}
