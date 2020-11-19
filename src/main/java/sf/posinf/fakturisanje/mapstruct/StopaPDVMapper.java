package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sf.posinf.fakturisanje.dto.StopaPDV_Dto;
import sf.posinf.fakturisanje.model.StopaPDV;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StopaPDVMapper {

	@Mapping(source = "pdv.id", target = "pdv")
	StopaPDV_Dto stopaPdvToDto(StopaPDV stopaPdv);
	
	@Mapping(source = "pdv", target = "pdv.id")
	StopaPDV stopaPdvDtoToEntity(StopaPDV_Dto stopaPdv_Dto);
	
	List<StopaPDV> stopaPdvDtoToEntity(List<StopaPDV_Dto> stopePDV);
	
	List<StopaPDV_Dto> stopaPdvToDto(List<StopaPDV> stopePDV);
}
