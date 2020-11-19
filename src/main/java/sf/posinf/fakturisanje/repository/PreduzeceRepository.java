package sf.posinf.fakturisanje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sf.posinf.fakturisanje.model.Preduzece;

import java.util.List;

@Repository
public interface PreduzeceRepository extends JpaRepository<Preduzece,Long> {

    List<Preduzece> findAllByObrisano(boolean obrisano);

    Preduzece findByObrisanoAndId(boolean obrisano, long id);

}
