package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sf.posinf.fakturisanje.dto.StopaPDV_Dto;
import sf.posinf.fakturisanje.model.StopaPDV;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PDVMapper.class})
public interface StopaPDVMapper {

	StopaPDV_Dto stopaPdvToDto(StopaPDV stopaPdv);
	
	StopaPDV stopaPdvDtoToEntity(StopaPDV_Dto stopaPdv_Dto);
	
	List<StopaPDV> stopaPdvDtoToEntity(List<StopaPDV_Dto> stopePDV);
	
	List<StopaPDV_Dto> stopaPdvToDto(List<StopaPDV> stopePDV);
}
