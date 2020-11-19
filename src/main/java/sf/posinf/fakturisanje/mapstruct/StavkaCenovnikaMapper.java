package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sf.posinf.fakturisanje.dto.StavkaCenovnikaDTO;
import sf.posinf.fakturisanje.model.StavkaCenovnika;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StavkaCenovnikaMapper {
	@Mapping(source = "robaUsluga.id", target = "robaUsluga")
	@Mapping(source = "cenovnik.id", target = "cenovnik")
	StavkaCenovnikaDTO stavkaCenovnikaToDto(StavkaCenovnika stavkaCenovnika);

	@Mapping(source = "robaUsluga", target = "robaUsluga.id")
	@Mapping(source = "cenovnik", target = "cenovnik.id")
	StavkaCenovnika stavkaCenovnikaDtoToEntity(StavkaCenovnikaDTO stavkaCenovnikaDTO);
	
	List<StavkaCenovnika> stavkaCenovnikaDtoToEntity(List<StavkaCenovnikaDTO> stavkeCenovnika);
	
	List<StavkaCenovnikaDTO> stavkaCenovnikaToDto(List<StavkaCenovnika> stavkeCenovnika);
}
