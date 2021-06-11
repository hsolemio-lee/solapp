package com.sol.solapp.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String smartThingsToken;
    private String authToken;
}
