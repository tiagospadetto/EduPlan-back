package br.com.eduplan.controllers;

import br.com.eduplan.domain.dto.AuthenticationDTO;
import br.com.eduplan.domain.dto.LoginResponseDTO;
import br.com.eduplan.domain.dto.RegisterDTO;
import br.com.eduplan.domain.dto.SucessResponse;
import br.com.eduplan.services.AuthenticationService;
import br.com.eduplan.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    AuthenticationService authenticationService;
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDTO> login (@RequestBody @Valid AuthenticationDTO data){
       LoginResponseDTO dto = authenticationService.login(data);

       return ResponseEntity.ok(dto);
    }

}
