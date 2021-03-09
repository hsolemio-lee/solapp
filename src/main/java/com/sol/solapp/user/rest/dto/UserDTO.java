package com.sol.solapp.user.rest.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
}
