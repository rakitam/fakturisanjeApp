package sf.posinf.fakturisanje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.fakturisanje.model.StavkaFakture;

@Repository
public interface StavkaFaktureRepository extends JpaRepository<StavkaFakture,Long> {

	List<StavkaFakture> findByFaktura_id(Long id);
}
