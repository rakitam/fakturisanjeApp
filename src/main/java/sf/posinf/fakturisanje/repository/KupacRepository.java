package sf.posinf.fakturisanje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.fakturisanje.model.Kupac;

@Repository
public interface KupacRepository extends JpaRepository<Kupac, Long> {

    List<Kupac> findAllByObrisano(boolean obrisano);

    Kupac findByObrisanoAndId(boolean obrisano, long id);

    List<Kupac> findByPreduzece_id(Long id);

    List<Kupac>
    findAllByNazivPartneraIgnoreCaseContainsOrAdresaIgnoreCaseContainsOrMesto_NazivIgnoreCaseContainsAndObrisano(
            String nazivPartnera, String adresa, String naziv,
            boolean obrisano);

}
