package br.com.eduplan.services;

import br.com.eduplan.domain.dto.AuthenticationDTO;
import br.com.eduplan.domain.dto.LoginResponseDTO;
import br.com.eduplan.domain.entity.User;
import br.com.eduplan.exceptions.UserInactiveException;
import br.com.eduplan.infra.security.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsersService usersService;

    public AuthenticationService(AuthenticationManager authenticationManager, TokenService tokenService, UsersService usersService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usersService = usersService;
    }

    public LoginResponseDTO login (AuthenticationDTO data){

        //Validar informações de login
        validLogin(data);

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new LoginResponseDTO(token);
    }

    private void validLogin(AuthenticationDTO data){

        User user = (User) this.usersService.findByEmail(data.email());

        if(user == null){
            throw new BadCredentialsException("Credenciais inválidas.");
        }
        if(!user.isAccountNonLocked()){
            throw new UserInactiveException("Usuário inativo.");
        }

    }
}
