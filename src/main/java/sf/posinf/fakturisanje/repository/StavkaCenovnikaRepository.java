package sf.posinf.fakturisanje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.fakturisanje.model.StavkaCenovnika;

@Repository
public interface StavkaCenovnikaRepository extends JpaRepository<StavkaCenovnika,Long>{


}
