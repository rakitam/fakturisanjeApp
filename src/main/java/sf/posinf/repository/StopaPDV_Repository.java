package sf.posinf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.model.StopaPDV;

public interface StopaPDV_Repository {

	
	@Repository
	public interface StopaPdvRepository extends JpaRepository<StopaPDV,Long>{


	}

	
	
	
}
