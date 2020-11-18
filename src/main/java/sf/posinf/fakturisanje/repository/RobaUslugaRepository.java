package sf.posinf.fakturisanje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.fakturisanje.model.RobaUsluga;

@Repository
public interface RobaUslugaRepository extends JpaRepository<RobaUsluga,Long>{

	Page<RobaUsluga> findAllByGrupaRobe_idAndObrisanoAndNazivRobeUslugeIgnoreCaseContains(Long idGrupe,
            boolean obrisano, String nazivRobeUsluge, Pageable pageable);
	
	Page<RobaUsluga> findAllByNazivRobeUslugeIgnoreCaseContains(String nazivRobeUsluge, Pageable pageable);
	
}
