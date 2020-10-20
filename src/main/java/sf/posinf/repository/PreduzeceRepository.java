package sf.posinf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.model.Preduzece;

@Repository
public interface PreduzeceRepository extends JpaRepository<Preduzece,Long> {

    List<Preduzece> findAllByObrisano(boolean obrisano);

    Preduzece findByObrisanoAndId(boolean obrisano, long id);

}
