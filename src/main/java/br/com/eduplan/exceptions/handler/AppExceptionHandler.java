package br.com.eduplan.exceptions.handler;

import br.com.eduplan.exceptions.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class AppExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleOtherExceptions(MethodArgumentNotValidException ex) {

        log.error("Erro ao cadastrar usuário:" + ex.getMessage());

        List<FieldError> fields = ex.getFieldErrors();
        List<ErrorResponse> erros = new ArrayList<>();

        for (FieldError field : fields) {
            ErrorResponse errorField = new ErrorResponse();
            errorField.setMessage(field.getField() +" " +  field.getDefaultMessage());
            errorField.setStatusCode(HttpStatus.BAD_REQUEST.value());

            erros.add(errorField);
        }

        return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
    }

}
