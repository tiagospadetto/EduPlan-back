package br.com.eduplan.services;

import br.com.eduplan.domain.dto.RegisterDTO;
import br.com.eduplan.domain.entity.User;
import br.com.eduplan.exceptions.EmailAlreadyExistsException;
import br.com.eduplan.exceptions.UserNotFoundException;
import br.com.eduplan.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    public void save(RegisterDTO registerDTO) {

        //Validar informações do Registro
        validRegister(registerDTO);

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User newUser = new User(registerDTO.email(), encryptedPassword, registerDTO.role(),registerDTO.name(), registerDTO.lastName());

        this.usersRepository.save(newUser);
    }

    private void validRegister(RegisterDTO registerDTO){

        if(this.usersRepository.findByEmail(registerDTO.email()) != null){
            throw new EmailAlreadyExistsException("Email já está em uso.");
        }
    }
    public void toggleUserActivation(Long userId) {
        Optional<User> optionalUser = usersRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEnabled(!user.isEnabled()); // Inverte o estado de ativação
            usersRepository.save(user);
        } else {
            throw new UserNotFoundException("Usuário não encontrado");
        }
    }

    public UserDetails findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
}