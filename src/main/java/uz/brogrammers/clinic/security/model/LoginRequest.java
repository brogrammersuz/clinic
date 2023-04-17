package uz.brogrammers.clinic.security.model;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;

}
