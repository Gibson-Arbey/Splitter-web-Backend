package co.edu.comfanorte.splitter.service.implementation;

import co.edu.comfanorte.splitter.model.entity.TemaEntity;
import co.edu.comfanorte.splitter.repository.TemaRepositoy;
import co.edu.comfanorte.splitter.service.interfaces.TemaInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TemaService implements TemaInterface {

    private final TemaRepositoy repositoy;

    @Override
    public List<TemaEntity> getTemas() {
        return repositoy.findAll();
    }
}
