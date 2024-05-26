package co.edu.comfanorte.splitter.service.implementation;

import co.edu.comfanorte.splitter.model.dto.ResultadoDTO;
import co.edu.comfanorte.splitter.model.entity.ResultadoEntity;
import co.edu.comfanorte.splitter.model.entity.TemaEntity;
import co.edu.comfanorte.splitter.model.entity.UsuarioEntity;
import co.edu.comfanorte.splitter.repository.ResultadoRepository;
import co.edu.comfanorte.splitter.repository.TemaRepositoy;
import co.edu.comfanorte.splitter.repository.UsuarioRepository;
import co.edu.comfanorte.splitter.service.interfaces.ResultadoInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ResultadoService implements ResultadoInterface {

    private final ResultadoRepository repository;

    private final TemaRepositoy temaRepositoy;

    private final UsuarioRepository usuarioRepository;

    @Override
    public void registerResult(ResultadoDTO resultado) {
        TemaEntity tema= temaRepositoy.findById(resultado.getIdTema()).orElseThrow();
        UsuarioEntity usuario = usuarioRepository.findById(resultado.getIdUser()).orElseThrow();
        ResultadoEntity result = ResultadoEntity.builder()
                .puntaje(resultado.getPuntaje())
                .tema(tema).usuario(usuario).build();
        repository.save(result);
    }

    @Override
    public List<ResultadoEntity> getResults(Integer nombreTema, Integer username) {
        return repository.findByUserAndTema(nombreTema, username);
    }
}
