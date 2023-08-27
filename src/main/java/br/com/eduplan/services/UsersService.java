package br.com.eduplan.services;

import br.com.eduplan.domain.dto.AuthenticationDTO;
import br.com.eduplan.domain.dto.LoginResponseDTO;
import br.com.eduplan.domain.dto.RegisterDTO;
import br.com.eduplan.domain.entity.User;
import br.com.eduplan.infra.security.TokenService;
import br.com.eduplan.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsersService {

    private UsersRepository usersRepository;
    private AuthenticationManager authenticationManager;

    private TokenService tokenService;


    public UsersService( UsersRepository usersRepository, AuthenticationManager authenticationManager,TokenService tokenService) {
        this.usersRepository = usersRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }
    public LoginResponseDTO login (AuthenticationDTO data){

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new LoginResponseDTO(token);
    }
    public void register(RegisterDTO registerDTO) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User newUser = new User(registerDTO.email(), encryptedPassword, registerDTO.role());

        this.usersRepository.save(newUser);
    }
}