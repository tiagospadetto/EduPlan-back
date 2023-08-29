package br.com.eduplan.services;

import br.com.eduplan.domain.dto.AuthenticationDTO;
import br.com.eduplan.domain.dto.LoginResponseDTO;
import br.com.eduplan.domain.dto.RegisterDTO;
import br.com.eduplan.domain.entity.User;
import br.com.eduplan.exceptions.EmailAlreadyExistsException;
import br.com.eduplan.exceptions.UserInactiveException;
import br.com.eduplan.infra.security.TokenService;
import br.com.eduplan.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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

        //Validar informações de login
        validLogin(data);

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new LoginResponseDTO(token);
    }
    public void register(RegisterDTO registerDTO) {

        //Validar informações do Registro
        validRegister(registerDTO);

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User newUser = new User(registerDTO.email(), encryptedPassword, registerDTO.role(),registerDTO.name(), registerDTO.lastName());

        this.usersRepository.save(newUser);
    }

    private void validLogin(AuthenticationDTO data){

        User user = (User) this.usersRepository.findByEmail(data.email());

        if(user == null){
            throw new BadCredentialsException("Credenciais inválidas.");
        }
        if(user.isAccountNonExpired()){
            throw new UserInactiveException("Usuário inativo.");
        }

    }

    private void validRegister(RegisterDTO registerDTO){

        if(this.usersRepository.findByEmail(registerDTO.email()) != null){
            throw new EmailAlreadyExistsException("Email já está em uso.");
        }
    }
}