package sf.posinf.fakturisanje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sf.posinf.fakturisanje.model.PDV;
import sf.posinf.fakturisanje.model.StopaPDV;

import java.util.List;

@Repository
public interface StopaPDVRepository extends JpaRepository<StopaPDV, Long>{

    List<StopaPDV> findAllByPdv_IdAndActiveIsTrue(long pdvId);
}
