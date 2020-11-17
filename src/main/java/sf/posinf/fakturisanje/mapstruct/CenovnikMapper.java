package sf.posinf.fakturisanje.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;

import sf.posinf.fakturisanje.dto.CenovnikDTO;
import sf.posinf.fakturisanje.dto.FakturaDto;
import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.Faktura;

@Mapper(componentModel = "spring")
public interface CenovnikMapper {
	
	CenovnikDTO cenovnikToDto(Cenovnik cenovnik);

	Cenovnik cenovnikDtoToEntity(CenovnikDTO cenovnikDto);
	
	List<Cenovnik> cenovnikDtoToEntity(List<CenovnikDTO> cenovnici);
	
	List<CenovnikDTO> cenovnikToDto(List<Cenovnik> cenovnici);
}
