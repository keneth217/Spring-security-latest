package com.example.security.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequetDto {
    public Object getLastname;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
