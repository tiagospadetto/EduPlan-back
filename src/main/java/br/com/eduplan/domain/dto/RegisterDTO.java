package br.com.eduplan.domain.dto;

import br.com.eduplan.domain.enums.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(@Email @NotNull String email, @NotNull String password, @NotNull UserRoleEnum role, @NotNull String name, @NotNull String lastName) {

}
