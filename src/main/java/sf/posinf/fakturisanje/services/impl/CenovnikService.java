package sf.posinf.fakturisanje.services.impl;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.StavkaCenovnika;
import sf.posinf.fakturisanje.repository.CenovnikRepository;
import sf.posinf.fakturisanje.repository.StavkaCenovnikaRepository;
import sf.posinf.fakturisanje.services.interfaces.CenovnikServiceInterface;

public class CenovnikService implements CenovnikServiceInterface {

    @Autowired
    CenovnikRepository cenovnikRepository;

    @Autowired
    StavkaCenovnikaRepository stavkaCenovnikaRepository;

    @Override
    public List<Cenovnik> findAll(int brojStranice, int brojPrikazanih) {
        return cenovnikRepository.findAllByObrisano(false);
    }

    @Override
    public Cenovnik findOne(Long id) {
        return cenovnikRepository.findByObrisanoAndId(false,id);
    }

    @Override
    public Cenovnik save(Cenovnik cenovnik) {
        cenovnikRepository.save(cenovnik);
        return cenovnik;
    }

    @Override
    public List<StavkaCenovnika> findAllByCenovnikId(long id, String nazivRobeUsluge, int brojStranice, int brojPrikazanih) {
        return stavkaCenovnikaRepository.findAllByObrisanoAndCenovnik_IdAndRobaUsluga_NazivRobeUslugeIgnoreCaseContains(
                false, id, nazivRobeUsluge);
    }

    @Override
    public Boolean delete(Long id) {
        Cenovnik cenovnik = cenovnikRepository.findById(id).orElse(null);
        cenovnik.setObrisano(true);        
        cenovnikRepository.saveAndFlush(cenovnik);
        return true;
    }
}
