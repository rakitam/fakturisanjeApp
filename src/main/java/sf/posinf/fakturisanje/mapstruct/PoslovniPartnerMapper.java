package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import sf.posinf.fakturisanje.dto.PoslovniPartnerDto;
import sf.posinf.fakturisanje.model.Kupac;

@Mapper(componentModel = "spring") 
public interface PoslovniPartnerMapper {

	@Mapping(source = "preduzece.id", target = "preduzece")
	@Mapping(source = "mesto.id", target = "mesto")
	PoslovniPartnerDto poslovniPartnerToDto(Kupac poslovniPartner);
	@Mapping(source = "preduzece", target = "preduzece.id")
	@Mapping(source = "mesto", target = "mesto.id")
	Kupac poslovniPartnerDtotoEntity(PoslovniPartnerDto poslovniPartnerDto);
}
