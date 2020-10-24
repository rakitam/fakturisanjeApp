package sf.posinf.fakturisanje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.fakturisanje.model.StopaPDV;

@Repository
public interface StopaPDVRepository extends JpaRepository<StopaPDV, Long>{

}
