package sf.posinf.services.interfaces;

import java.util.List;

import sf.posinf.model.RobaUsluga;

public interface RobaUslugaServiceInterface {

	List<RobaUsluga> findAll(String naziv, int brojStanice, int brojPrikazanih);
    RobaUsluga findOne(Long id);
    RobaUsluga save(RobaUsluga robaUsluga);
    List<RobaUsluga> findAllByGrupaRobe_id(Long id, String naziv, int brojStanice, int brojPrikazanih);
	
}
