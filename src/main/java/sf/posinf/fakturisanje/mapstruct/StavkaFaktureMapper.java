package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sf.posinf.fakturisanje.dto.StavkaFaktureDto;
import sf.posinf.fakturisanje.model.StavkaFakture;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RobaUslugaMapper.class})
public interface StavkaFaktureMapper {

	@Mapping(source = "faktura.id", target = "faktura")
	StavkaFaktureDto stavkaFaktureToDto(StavkaFakture stavkaFakture);
	
	@Mapping(source = "faktura", target = "faktura.id")
	StavkaFakture stavkaFaktureDtoToEntity(StavkaFaktureDto stavkaFaktureDto);
	
	List<StavkaFakture> stavkaFaktureDtoToEntity(List<StavkaFaktureDto> stavkeFakture);
	
	List<StavkaFaktureDto> stavkaFaktureToDto(List<StavkaFakture> stavkeFakture);
}
