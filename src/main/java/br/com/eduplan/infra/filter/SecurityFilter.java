package br.com.eduplan.infra.filter;

import br.com.eduplan.exceptions.model.ErrorResponse;
import br.com.eduplan.infra.security.TokenService;
import br.com.eduplan.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final List<String> excludeUrlPatterns = new ArrayList<>(Arrays.asList(
            "swagger-ui/index.html",
            "swagger-ui/swagger-ui.css",
            "swagger-ui/swagger-ui-bundle.js",
            "swagger-ui/index.css",
            "swagger-ui/swagger-ui-standalone-preset.js",
            "swagger-ui/swagger-initializer.js",
            "swagger-ui/favicon-32x32.png",
            "v3/api-docs/swagger-config",
            "swagger-ui/favicon-32x32.png",
            "v3/api-docs",
            "/auth/login"
    ));
    @Autowired
    TokenService tokenService;
    @Autowired
    UsersRepository userRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token != null && !tokenService.isTokenExpired(token)){
            var email = tokenService.validateToken(token);
            UserDetails user = userRepository.findByEmail(email);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else{
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");

            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Token inválido");
            errorResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());

            ObjectMapper objectMapper = new ObjectMapper();
            String errorResponseJson = objectMapper.writeValueAsString(errorResponse);

            response.getWriter().write(errorResponseJson);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)  {
        String path = request.getRequestURI().replace("/api/v1/", "");
        return excludeUrlPatterns.contains(path);
    }
}
