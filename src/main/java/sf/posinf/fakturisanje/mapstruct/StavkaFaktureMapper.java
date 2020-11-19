package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sf.posinf.fakturisanje.dto.StavkaFaktureDto;
import sf.posinf.fakturisanje.model.StavkaFakture;

import java.util.List;

@Mapper(componentModel = "spring") 
public interface StavkaFaktureMapper {

	@Mapping(source = "faktura.id", target = "faktura")
	@Mapping(source = "robaUsluga.id", target = "robaUsluga")
	StavkaFaktureDto stavkaFaktureToDto(StavkaFakture stavkaFakture);
	
	@Mapping(source = "faktura", target = "faktura.id")
	@Mapping(source = "robaUsluga", target = "robaUsluga.id")
	StavkaFakture stavkaFaktureDtoToEntity(StavkaFaktureDto stavkaFaktureDto);
	
	List<StavkaFakture> stavkaFaktureDtoToEntity(List<StavkaFaktureDto> stavkeFakture);
	
	List<StavkaFaktureDto> stavkaFaktureToDto(List<StavkaFakture> stavkeFakture);
}
