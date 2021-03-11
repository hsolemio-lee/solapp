package com.sol.solapp.user.service.helper;

import com.sol.solapp.user.entity.User;
import com.sol.solapp.user.rest.dto.UserDTO;
import lombok.experimental.UtilityClass;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@UtilityClass
public class UserServiceImplTestHelper {

    public UserDTO getMockUserDTO(Long id) {
        return UserDTO.builder()
                .id(id)
                .firstName("sol")
                .lastName("lee")
                .email("test"+id+"@test.com")
                .build();
    }

    public User getMockCreatedUser(Long id) {
        return User.builder()
                .id(id)
                .firstName("sol")
                .lastName("lee")
                .email("test"+id+"@test.com")
                .createdTimestamp(new Date())
                .lastModifiedTimestamp(new Date())
                .build();
    }

    public List<User> getMockUserList(long size) {
        return LongStream.range(1, size+1)
                .mapToObj(UserServiceImplTestHelper::getMockCreatedUser)
                .collect(Collectors.toList());
    }

    public MockMultipartFile getMockMultipartFile(String type, String content) {
        return new MockMultipartFile("user-file","test.csv",
                type, content.getBytes());
    }
}
