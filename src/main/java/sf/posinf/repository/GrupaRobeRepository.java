package sf.posinf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.model.GrupaRobe;

@Repository
public interface GrupaRobeRepository extends JpaRepository<GrupaRobe, Long>{

	List<GrupaRobe> findAllByPreduzece_idAndObrisano(Long id);
	List<GrupaRobe> findAllByObrisanoAndNazivGrupeIgnoreCaseContains(String naziv);
	
}
