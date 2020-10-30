package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;

import sf.posinf.fakturisanje.dto.MestoDto;
import sf.posinf.fakturisanje.model.Mesto;

@Mapper(componentModel = "spring") 
public interface MestoMapper {
	
	MestoDto mestoToDto(Mesto mesto);
	Mesto mestoDtoToEntity(MestoDto mestoDto);
}
