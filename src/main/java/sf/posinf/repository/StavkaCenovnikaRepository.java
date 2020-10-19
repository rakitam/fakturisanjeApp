package sf.posinf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.model.StavkaCenovnika;

@Repository
public interface StavkaCenovnikaRepository extends JpaRepository<StavkaCenovnika,Long>{


}
