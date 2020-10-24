package sf.posinf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.model.RobaUsluga;

@Repository
public interface RobaUslugaRepository extends JpaRepository<RobaUsluga,Long>{

	List<RobaUsluga> findAllByObrisanoAndNazivRobeUslugeIgnoreCaseContains(String nazivRobeUsluge);
	List<RobaUsluga> findAllByGrupaRobe_idAndObrisanoAndNazivRobeUslugeIgnoreCaseContains(Long id, String nazivRobeUsluge);
	
}
