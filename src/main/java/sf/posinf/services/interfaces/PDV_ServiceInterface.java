package sf.posinf.services.interfaces;

import java.util.List;
import sf.posinf.model.PDV;

public interface PDV_ServiceInterface {

    List<PDV> findAll();
    PDV findOne(Long id);
    PDV save(PDV pdv);
}
