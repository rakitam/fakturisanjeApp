package sf.posinf.fakturisanje.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.fakturisanje.model.StavkaCenovnika;

@Repository
public interface StavkaCenovnikaRepository extends JpaRepository<StavkaCenovnika,Long>{

    List<StavkaCenovnika> findAllByObrisano(boolean obrisano);

    StavkaCenovnika findByObrisanoAndId(boolean obrisano, long id);
    
    List<StavkaCenovnika> findAllByObrisanoAndRobaUsluga_Id(boolean obrisano, Long id);

    List<StavkaCenovnika> findAllByObrisanoAndCenovnik_IdAndRobaUsluga_NazivRobeUslugeIgnoreCaseContains(
            boolean obrisano, long cenovnik_id, String nazivRobeUsluge);
}
