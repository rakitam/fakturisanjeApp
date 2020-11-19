package sf.posinf.fakturisanje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sf.posinf.fakturisanje.model.StavkaCenovnika;

import java.util.List;

@Repository
public interface StavkaCenovnikaRepository extends JpaRepository<StavkaCenovnika,Long>{
    
    List<StavkaCenovnika> findAllByRobaUsluga_Id(Long id);

    Page<StavkaCenovnika> findAllByCenovnik_IdAndRobaUsluga_NazivRobeUslugeIgnoreCaseContains(long cenovnik_id, String nazivRobeUsluge, Pageable pageable);
}
