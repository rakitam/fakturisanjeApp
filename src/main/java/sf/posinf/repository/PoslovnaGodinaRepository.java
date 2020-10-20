package sf.posinf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.model.PoslovnaGodina;

@Repository
public interface PoslovnaGodinaRepository extends JpaRepository<PoslovnaGodina, Long>{

	List<PoslovnaGodina> findByZakljucanaIsFalse();
}
