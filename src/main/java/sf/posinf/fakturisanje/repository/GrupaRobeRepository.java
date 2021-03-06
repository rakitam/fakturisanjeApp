package sf.posinf.fakturisanje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sf.posinf.fakturisanje.model.GrupaRobe;

import java.util.List;

@Repository
public interface GrupaRobeRepository extends JpaRepository<GrupaRobe, Long>{
	
	List<GrupaRobe> findAllByPreduzece_id(Long id);
	
	List<GrupaRobe> findAllByNazivGrupeIgnoreCaseContains(String naziv);
	
	Page<GrupaRobe> findAllByNazivGrupeIgnoreCaseContains(String naziv, Pageable pageable);
	
}
