package com.sol.solapp.user.service;

import com.sol.solapp.common.exception.ServiceException;
import com.sol.solapp.common.exception.code.ErrorCode;
import com.sol.solapp.user.entity.User;
import com.sol.solapp.user.repository.UserRepository;
import com.sol.solapp.user.rest.dto.InsertUserReportDTO;
import com.sol.solapp.user.rest.dto.UserDTO;
import com.sol.solapp.user.service.impl.UserServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.sol.solapp.user.service.helper.UserServiceImplTestHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    private final String CSV_TYPE = "text/csv";
    private final String TXT_TYPE = "test";

    @Spy
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository mockUserRepository;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void whenCallCreateUser() {
        when(mockUserRepository.save(any())).thenReturn(getMockCreatedUser(1L));

        UserDTO result = userService.createUser(getMockUserDTO(1L));

        verify(mockUserRepository, times(1)).save(any());

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getFirstName()).isEqualTo("sol");
        assertThat(result.getLastName()).isEqualTo("lee");
        assertThat(result.getEmail()).isEqualTo("test1@test.com");
    }

    @Test
    public void whenCallCreateUsers() {
        when(mockUserRepository.saveAll(any())).thenReturn(getMockUserList(4));
        MockMultipartFile mockMultipartFile = getMockMultipartFile(CSV_TYPE
                , "id,firstname,lastname,email" +
                "\n1,sol,lee,test1@test.com" +
                "\n2,sol,lee,test2@test.com" +
                "\n3,sol,lee,test3@test.com" +
                "\n4,sol,lee,test4@test.com");

        InsertUserReportDTO result = userService.createUsers(mockMultipartFile);

        verify(mockUserRepository, times(1)).saveAll(any());
        verify(mockUserRepository, times(1)).flush();

        assertThat(result).hasFieldOrPropertyWithValue("insertedCount", 4L);
        assertThat(result).hasFieldOrPropertyWithValue("updatedCount", 0L);
        assertThat(result).hasFieldOrPropertyWithValue("failedCount", 0L);
        assertThat(result).hasFieldOrPropertyWithValue("totalCount", 4L);
    }

    @Test
    public void givenNotCsvFile_whenCallCreateUsers_thenThrowsException() {
        when(mockUserRepository.saveAll(any())).thenReturn(getMockUserList(4));
        MockMultipartFile mockMultipartFile = getMockMultipartFile(TXT_TYPE
                , "id,firstname,lastname,email" +
                        "\n1,sol,lee,test1@test.com" +
                        "\n2,sol,lee,test2@test.com" +
                        "\n3,sol,lee,test3@test.com" +
                        "\n4,sol,lee,test4@test.com");

        exception.expect(ServiceException.class);

        InsertUserReportDTO result = userService.createUsers(mockMultipartFile);

        verify(mockUserRepository, times(0)).saveAll(any());
        verify(mockUserRepository, times(0)).flush();
    }

    @Test
    public void given1IdEmptyContent_whenCallCreateUsers() {
        List<User> savedUsers = getMockUserList(4);
        savedUsers.remove(0);

        when(mockUserRepository.saveAll(any())).thenReturn(savedUsers);

        MockMultipartFile mockMultipartFile = getMockMultipartFile(CSV_TYPE
                , "id,firstname,lastname,email" +
                        "\n,sol,lee,test1@test.com" +
                        "\n2,sol,lee,test2@test.com" +
                        "\n3,sol,lee,test3@test.com" +
                        "\n4,sol,lee,test4@test.com");

        InsertUserReportDTO result = userService.createUsers(mockMultipartFile);

        verify(mockUserRepository, times(1)).saveAll(any());
        verify(mockUserRepository, times(1)).flush();

        assertThat(result).hasFieldOrPropertyWithValue("insertedCount", 3L);
        assertThat(result).hasFieldOrPropertyWithValue("updatedCount", 0L);
        assertThat(result).hasFieldOrPropertyWithValue("failedCount", 1L);
        assertThat(result).hasFieldOrPropertyWithValue("totalCount", 4L);
    }

    @Test
    public void given1InsertedRow_whenCallCreateUsers() {
        List<User> savedUsers = getMockUserList(4);
        savedUsers.get(0).setCreateDate(null);
        when(mockUserRepository.saveAll(any())).thenReturn(savedUsers);
        MockMultipartFile mockMultipartFile = getMockMultipartFile(CSV_TYPE
                , "id,firstname,lastname,email" +
                        "\n1,sol,lee,test1@test.com" +
                        "\n2,sol,lee,test2@test.com" +
                        "\n3,sol,lee,test3@test.com" +
                        "\n4,sol,lee,test4@test.com");

        InsertUserReportDTO result = userService.createUsers(mockMultipartFile);

        verify(mockUserRepository, times(1)).saveAll(any());
        verify(mockUserRepository, times(1)).flush();

        assertThat(result).hasFieldOrPropertyWithValue("insertedCount", 3L);
        assertThat(result).hasFieldOrPropertyWithValue("updatedCount", 1L);
        assertThat(result).hasFieldOrPropertyWithValue("failedCount", 0L);
        assertThat(result).hasFieldOrPropertyWithValue("totalCount", 4L);
    }

    @Test
    public void given1InsertedRowAnd1InvalidEmailFormat_whenCallCreateUsers() {
        List<User> savedUsers = getMockUserList(4);
        savedUsers.get(0).setCreateDate(null);
        savedUsers.remove(2);

        when(mockUserRepository.saveAll(any())).thenReturn(savedUsers);
        MockMultipartFile mockMultipartFile = getMockMultipartFile(CSV_TYPE
                , "id,firstname,lastname,email" +
                        "\n1,sol,lee,test1@test.com" +
                        "\n2,sol,lee,test2@test.com" +
                        "\n3,sol,lee,test3" +
                        "\n4,sol,lee,test4@test.com");

        InsertUserReportDTO result = userService.createUsers(mockMultipartFile);

        verify(mockUserRepository, times(1)).saveAll(any());
        verify(mockUserRepository, times(1)).flush();

        assertThat(result).hasFieldOrPropertyWithValue("insertedCount", 2L);
        assertThat(result).hasFieldOrPropertyWithValue("updatedCount", 1L);
        assertThat(result).hasFieldOrPropertyWithValue("failedCount", 1L);
        assertThat(result).hasFieldOrPropertyWithValue("totalCount", 4L);
    }

    @Test
    public void whenCallGetUsers() {
        List<User> savedUsers = getMockUserList(4);
        Page<User> userPage = new PageImpl<>(savedUsers, PageRequest.of(1, 4), 200L);
        when(mockUserRepository.findAll(any(Pageable.class))).thenReturn(userPage);

        Page<UserDTO> result = userService.getUsers(PageRequest.of(1, 4));

        verify(mockUserRepository, times(1)).findAll(any(Pageable.class));

        assertThat(result.getTotalElements()).isEqualTo(200L);
        assertThat(result.getContent()).hasSize(4);
    }

}
