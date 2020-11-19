package sf.posinf.fakturisanje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sf.posinf.fakturisanje.model.StavkaFakture;

import java.util.List;

@Repository
public interface StavkaFaktureRepository extends JpaRepository<StavkaFakture,Long> {

	List<StavkaFakture> findByFaktura_id(Long id);
}
