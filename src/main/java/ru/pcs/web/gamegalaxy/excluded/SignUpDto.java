package ru.pcs.web.gamegalaxy.dto;

import lombok.Data;

@Data
public class SignUpDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
