package co.edu.comfanorte.splitter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.edu.comfanorte.splitter.model.dto.ResponseDTO;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = { UsuarioException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handleUsuarioException(UsuarioException ex) {
        return ResponseEntity.badRequest().body(new ResponseDTO("error", ex.getMessage()));
    }

    @ExceptionHandler(value = { CursoException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handleCursoException(UsuarioException ex) {
        return ResponseEntity.badRequest().body(new ResponseDTO("error", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            errors.append(errorMessage).append(" ");
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseDTO("error", errors.toString().trim()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResponseEntity<ResponseDTO> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            new ResponseDTO("error", "Acceso denegado."));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException ex) {
        String errorMessage = "Parametro requerido '" + ex.getParameterName() + "' no presente.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseDTO("error", errorMessage));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> handleException( Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseDTO("error", ex.getMessage()));
    }
}
