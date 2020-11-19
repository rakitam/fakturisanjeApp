package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sf.posinf.fakturisanje.dto.CenovnikDTO;
import sf.posinf.fakturisanje.model.Cenovnik;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CenovnikMapper {

	@Mapping(source = "preduzece.id", target = "preduzece")
	CenovnikDTO cenovnikToDto(Cenovnik cenovnik);

	@Mapping(source = "preduzece", target = "preduzece.id")
	Cenovnik cenovnikDtoToEntity(CenovnikDTO cenovnikDto);
	
	List<Cenovnik> cenovnikDtoToEntity(List<CenovnikDTO> cenovnici);
	
	List<CenovnikDTO> cenovnikToDto(List<Cenovnik> cenovnici);
}
