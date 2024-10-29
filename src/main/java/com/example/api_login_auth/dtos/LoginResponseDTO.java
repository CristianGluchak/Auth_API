package com.example.api_login_auth.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 27/10/2024
 */
@Getter
@Builder
public class LoginResponseDTO {

    private final String name;

    private final String token;
}
