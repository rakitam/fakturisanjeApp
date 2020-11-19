package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sf.posinf.fakturisanje.dto.PreduzeceDto;
import sf.posinf.fakturisanje.model.Preduzece;

import java.util.List;

@Mapper(componentModel = "spring") 
public interface PreduzeceMapper {

	@Mapping(source = "mesto.id", target = "mesto")
	PreduzeceDto preduzeceToDto(Preduzece preduzece);
	
	@Mapping(source = "mesto", target = "mesto.id")
	Preduzece preduzeceDtoToEntity(PreduzeceDto preduzeceDto);
	
	List<Preduzece> preduzeceDtoToEntity(List<PreduzeceDto> preduzeca);
	
	List<PreduzeceDto> preduzeceToDto(List<Preduzece> preduzeca);
}
