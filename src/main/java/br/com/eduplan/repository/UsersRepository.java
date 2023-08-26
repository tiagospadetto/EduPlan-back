package br.com.eduplan.repository;

import br.com.eduplan.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository  extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);
}
