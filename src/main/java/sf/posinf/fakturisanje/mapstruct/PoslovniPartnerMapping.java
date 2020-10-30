package sf.posinf.fakturisanje.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import sf.posinf.fakturisanje.dto.PoslovniPartnerDto;
import sf.posinf.fakturisanje.model.PoslovniPartner;

@Mapper(componentModel = "spring") 
public interface PoslovniPartnerMapping {

	@Mapping(source = "preduzece.id", target = "preduzece")
	@Mapping(source = "mesto.id", target = "mesto")
	PoslovniPartnerDto poslovniPartnerToDto(PoslovniPartner poslovniPartner);
	@Mapping(source = "preduzece", target = "preduzece.id")
	@Mapping(source = "mesto", target = "mesto.id")
	PoslovniPartner poslovniPartnerDtotoEntity(PoslovniPartnerDto poslovniPartnerDto);
}
