package com.example.api_login_auth.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 27/10/2024
 */
@Getter
@Setter
public class LoginRequestDTO {

    private String email;

    private String password;
}
