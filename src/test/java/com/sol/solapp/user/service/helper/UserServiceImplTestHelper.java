package com.sol.solapp.user.service.helper;

import com.sol.solapp.user.entity.User;
import com.sol.solapp.user.rest.dto.UserDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserServiceImplTestHelper {

    public UserDTO getMockUserDTO(Long id) {
        return UserDTO.builder()
                .id(id)
                .firstName("sol")
                .lastName("lee")
                .email("test@test.com")
                .build();
    }

    public User getMockUser(Long id) {
        return User.builder()
                .id(id)
                .firstName("sol")
                .lastName("lee")
                .email("test@test.com")
                .build();
    }
}
