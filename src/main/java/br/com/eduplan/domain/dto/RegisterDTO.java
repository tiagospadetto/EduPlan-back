package br.com.eduplan.domain.dto;

import br.com.eduplan.domain.enums.UserRoleEnum;

public record RegisterDTO(String email, String password, UserRoleEnum role) {

}
