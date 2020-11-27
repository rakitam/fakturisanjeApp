package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sf.posinf.fakturisanje.dto.CenovnikDTO;
import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.Preduzece;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PreduzeceMapper.class})
public interface CenovnikMapper {

	CenovnikDTO cenovnikToDto(Cenovnik cenovnik);

	Cenovnik cenovnikDtoToEntity(CenovnikDTO cenovnikDto);
	
	List<Cenovnik> cenovnikDtoToEntity(List<CenovnikDTO> cenovnici);
	
	List<CenovnikDTO> cenovnikToDto(List<Cenovnik> cenovnici);
}
