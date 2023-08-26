package br.com.eduplan.domain.enums;

public enum UserRoleEnum {

    ADMIN("admin"),
    USER("user");

    private final String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
