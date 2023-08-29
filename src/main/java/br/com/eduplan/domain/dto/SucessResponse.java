package br.com.eduplan.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record SucessResponse(String message,int statusCode ) {
}
