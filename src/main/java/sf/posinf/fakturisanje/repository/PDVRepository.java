package sf.posinf.fakturisanje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.fakturisanje.model.PDV;

@Repository
public interface PDVRepository extends JpaRepository<PDV, Long>{

}
