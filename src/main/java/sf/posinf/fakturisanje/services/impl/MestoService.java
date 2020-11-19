package sf.posinf.fakturisanje.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sf.posinf.fakturisanje.model.Mesto;
import sf.posinf.fakturisanje.repository.MestoRepository;
import sf.posinf.fakturisanje.services.interfaces.MestoServiceInterface;

@Service
public class MestoService implements MestoServiceInterface {

	@Autowired
    MestoRepository mestoRepository;
	
	@Autowired
	MestoServiceInterface mestoServiceInterface;

    @Override
    public Page<Mesto> findAll(String naziv, boolean obrisano, Pageable pageable) {
        return mestoRepository.findAllByNazivIgnoreCaseContainsAndObrisano(naziv, false, pageable);
    }

    @Override
    public Mesto findOne(Long id) {
        return mestoRepository.findById(id).orElse(null);
    }

    @Override
    public Mesto save(Mesto mesto) {
        mestoRepository.save(mesto);
        return mesto;
    }

	@Override
	public Boolean delete(Long id) {
		Mesto mesto = mestoRepository.getOne(id);
		if (mesto == null) {
			return false;
		}
		mesto.setObrisano(true);
		mestoRepository.saveAndFlush(mesto);
		return true;
	}
}
