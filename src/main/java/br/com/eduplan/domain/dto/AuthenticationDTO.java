package br.com.eduplan.domain.dto;

import jakarta.validation.constraints.Email;

public record AuthenticationDTO(@Email String email, String password) {
}