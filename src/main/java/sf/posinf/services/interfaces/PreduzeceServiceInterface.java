package sf.posinf.services.interfaces;

import java.util.List;

import sf.posinf.model.Faktura;
import sf.posinf.model.Preduzece;

public interface PreduzeceServiceInterface {

    List<Preduzece> findAll();
    List<Faktura> findAllByPreduzeceAndVrstaFakture(boolean vrstaFakture,long id);
    List<Faktura> findAllByPreduzeceAndVrstaFaktureAndPlaceno(boolean vrstaFakture,long id, boolean placeno);
    Preduzece findOne(Long id);
    Preduzece save(Preduzece preduzece);
    Boolean delete(Long id);
}
