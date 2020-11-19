package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import sf.posinf.fakturisanje.dto.GrupaRobeDto;
import sf.posinf.fakturisanje.dto.MestoDto;
import sf.posinf.fakturisanje.model.Mesto;

import java.util.List;

@Mapper(componentModel = "spring") 
public interface MestoMapper {
	
	MestoDto mestoToDto(Mesto mesto);
	
	Mesto mestoDtoToEntity(MestoDto mestoDto);
	
	List<Mesto> mestoDtoToEntity(List<MestoDto> mesta);
	
	List<GrupaRobeDto> mestoToDto(List<Mesto> mesta);
}
