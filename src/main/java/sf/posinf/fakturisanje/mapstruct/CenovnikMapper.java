package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import sf.posinf.fakturisanje.dto.CenovnikDTO;
import sf.posinf.fakturisanje.model.Cenovnik;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CenovnikMapper {
	
	CenovnikDTO cenovnikToDto(Cenovnik cenovnik);

	Cenovnik cenovnikDtoToEntity(CenovnikDTO cenovnikDto);
	
	List<Cenovnik> cenovnikDtoToEntity(List<CenovnikDTO> cenovnici);
	
	List<CenovnikDTO> cenovnikToDto(List<Cenovnik> cenovnici);
}
