package sf.posinf.services.interfaces;

import java.util.List;

import sf.posinf.model.Faktura;

//TODO: Za sad dodato vise metoda koje bi se potencijalno mogle koristiti; posle pobrisati one koje ne budu bili potrebne
public interface FakturaServiceInterface {

	List<Faktura> findAll();
	
	Faktura findOne(Long id);
	
    List<Faktura> findAllByVrstaFakture(boolean vrstaFakture);
    
    List<Faktura> findAllByVrstaFaktureAndPoslovnaGodina(boolean vrstaFakture, long poslovnaGodina);
    
    List<Faktura> findAllByPoslovniPartner(String nazivPartnera);
    
    List<Faktura> findAllByVrstaFaktureAndPoslovniPartner(boolean vrstaFakture, String poslovniPartner);
    
    List<Faktura> findAllByVrstaFaktureAndPoslovniPartnerAndPoslovnaGodina(boolean vrstaFakture, String poslovniPartner, long poslovnaGodina);
    
    List<Faktura> findAllByPoslovnaGodina(long poslovnaGodina);
    
    List<Faktura> findAllByStatusFakture(String statusFakture);
    
    Faktura save(Faktura faktura);
    
    //Faktura moze biti obrisana samo u fazi formiranja!!!
    Boolean delete(Long id);
    
    void update(Faktura faktura);
}
