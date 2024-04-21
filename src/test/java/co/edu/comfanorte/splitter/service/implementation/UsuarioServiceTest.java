package co.edu.comfanorte.splitter.service.implementation;

import co.edu.comfanorte.splitter.model.entity.UsuarioEntity;
import co.edu.comfanorte.splitter.repository.UsuarioRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void testBuscarUsuarioEmail() {
        String correo = "test@test.com";
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setCorreo(correo);

        when(usuarioRepository.findByCorreo(correo)).thenReturn(Optional.of(usuarioEntity));

        UsuarioEntity result = usuarioService.buscarUsuarioEmail(correo);

        assertEquals(correo, result.getCorreo());
    }

    @Test
    public void testGuardarUsuario() {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setCorreo("test@test.com");

        when(usuarioRepository.save(usuarioEntity)).thenReturn(usuarioEntity);

        usuarioService.guardarUsuario(usuarioEntity, "curso");

        assertEquals("test@test.com", usuarioEntity.getCorreo());
    }

    @Test
    public void testCambiarContrasenia() {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(1);
        usuarioEntity.setContrasena("oldPassword");

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioEntity));

        usuarioService.cambiarContrasenia(1, "newPassword");

        assertEquals("newPassword", usuarioEntity.getContrasena());
    }

}