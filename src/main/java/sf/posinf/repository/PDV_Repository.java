package sf.posinf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.model.PDV;

public interface PDV_Repository {

	
	@Repository
	public interface PDVRepository extends JpaRepository<PDV, Long> {
		
	}
	
	
	
}
