package com.sol.solapp.user.service;

import com.sol.solapp.user.rest.dto.InsertUserReportDTO;
import com.sol.solapp.user.rest.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user);

    InsertUserReportDTO createUsers(MultipartFile file);

    Page<UserDTO> getUsers(Pageable pageable);

    UserDTO joinUser(UserDTO user);
}
