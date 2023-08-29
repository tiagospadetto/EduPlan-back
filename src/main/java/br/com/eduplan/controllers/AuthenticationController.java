package br.com.eduplan.controllers;

import br.com.eduplan.domain.dto.AuthenticationDTO;
import br.com.eduplan.domain.dto.LoginResponseDTO;
import br.com.eduplan.domain.dto.RegisterDTO;
import br.com.eduplan.domain.dto.SucessResponse;
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
    UsersService usersService;
    public AuthenticationController(UsersService usersService) {
        this.usersService = usersService;
    }
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO data){
       LoginResponseDTO dto = usersService.login(data);

       return ResponseEntity.ok(dto);
    }
    @PostMapping(value = "/register",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register (@RequestBody @Valid RegisterDTO data){

        this.usersService.register(data);
        SucessResponse ressponse = new SucessResponse("Usuário salvo com sucesso!", 200);
                
        return ResponseEntity.ok(ressponse);
    }
}
