package com.sol.solapp.user.service;

import com.sol.solapp.user.repository.UserRepository;
import com.sol.solapp.user.rest.dto.UserDTO;
import com.sol.solapp.user.service.helper.UserServiceImplTestHelper;
import com.sol.solapp.user.service.impl.UserServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import static com.sol.solapp.user.service.helper.UserServiceImplTestHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Spy
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository mockUserRepository;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void whenCallCreateUser() {
        when(mockUserRepository.save(any())).thenReturn(getMockUser(1L));

        UserDTO result = userService.createUser(getMockUserDTO(1L));

        verify(mockUserRepository, times(1)).save(any());

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getFirstName()).isEqualTo("sol");
        assertThat(result.getLastName()).isEqualTo("lee");
        assertThat(result.getEmail()).isEqualTo("test@test.com");
    }

    @Test
    public void whenCallCreateUsers() {

    }


}
