package br.com.eduplan.controllers;

import br.com.eduplan.domain.dto.RegisterDTO;
import br.com.eduplan.domain.dto.SucessResponse;
import br.com.eduplan.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SucessResponse> save (@RequestBody @Valid RegisterDTO data){
        this.usersService.save(data);
        SucessResponse ressponse = new SucessResponse("Usuário salvo com sucesso!", 200);
        return ResponseEntity.ok(ressponse);
    }
    @PutMapping(value = "/{userId}/toggle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SucessResponse> toggleUserActivation(@PathVariable Long userId) {
        usersService.toggleUserActivation(userId);
        SucessResponse response = new SucessResponse("Status do usuário alterado com sucesso!", 200);
        return ResponseEntity.ok(response);
    }
}
