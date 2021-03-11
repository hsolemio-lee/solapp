package com.sol.solapp.user.service;

import com.sol.solapp.user.rest.dto.InsertUserReportDTO;
import com.sol.solapp.user.rest.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    UserDTO createUser(UserDTO user);

    InsertUserReportDTO createUsers(MultipartFile file);

}
