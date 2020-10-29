package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;

import sf.posinf.fakturisanje.dto.CenovnikDTO;
import sf.posinf.fakturisanje.model.Cenovnik;

@Mapper(componentModel = "spring")
public interface CenovnikMapper {
	
	CenovnikDTO cenovnikToDto(Cenovnik cenovnik);

	Cenovnik cenovnikDtoToEntity(CenovnikDTO cenovnikDto);
}
