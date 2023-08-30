package br.com.eduplan.exceptions.handler;

import br.com.eduplan.exceptions.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Acesso negado - você não tem a ROLE necessária para acessar este recurso");
        errorResponse.setStatusCode(HttpStatus.FORBIDDEN.value());

        ObjectMapper objectMapper = new ObjectMapper();
        String errorResponseJson = objectMapper.writeValueAsString(errorResponse);

        response.getWriter().write(errorResponseJson);
    }
}
