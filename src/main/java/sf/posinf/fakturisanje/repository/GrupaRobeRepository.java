package sf.posinf.fakturisanje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.fakturisanje.model.GrupaRobe;

@Repository
public interface GrupaRobeRepository extends JpaRepository<GrupaRobe, Long>{

	List<GrupaRobe> findAllByPreduzece_id(Long id);
	
	List<GrupaRobe> findAllByNazivGrupeIgnoreCaseContains(String naziv);
	
}
