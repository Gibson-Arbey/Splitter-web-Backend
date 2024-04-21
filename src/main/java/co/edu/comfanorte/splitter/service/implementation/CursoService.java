package co.edu.comfanorte.splitter.service.implementation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.comfanorte.splitter.model.dto.CursoDTO;
import co.edu.comfanorte.splitter.model.entity.CursoEntity;
import co.edu.comfanorte.splitter.model.entity.UsuarioCursoEntity;
import co.edu.comfanorte.splitter.repository.UsuarioCursoRepository;
import co.edu.comfanorte.splitter.service.interfaces.CursoInterface;


@Service
public class CursoService implements CursoInterface{
    


    @Autowired
    private UsuarioCursoRepository usuarioCursoRepository;

    @Override
    public List<CursoEntity> getCursosByUsuarioId(Integer usuarioId) {
        return usuarioCursoRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(UsuarioCursoEntity::getCurso)
                .collect(Collectors.toList());
    }
    
}
