package com.sol.solapp.user.service.impl;

import com.sol.solapp.common.exception.ServiceException;
import com.sol.solapp.common.exception.code.ErrorCode;
import com.sol.solapp.common.util.CSVUtil;
import com.sol.solapp.user.entity.User;
import com.sol.solapp.user.entity.converter.UserConverter;
import com.sol.solapp.user.repository.UserRepository;
import com.sol.solapp.user.rest.dto.UserDTO;
import com.sol.solapp.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO dto) {
        User user = UserConverter.INSTANCE.toEntity(dto);
        userRepository.save(user);
        return UserConverter.INSTANCE.toDto(user);
    }

    @Override
    public int createUser(MultipartFile file) {
        if(!CSVUtil.hasCSVFormat(file)) {
            throw new ServiceException(ErrorCode.INVALID_CSV_FILE);
        }
        List<User> users = new ArrayList<>();
        try {
            users = CSVUtil.convertCsvToUser(file.getInputStream());
            userRepository.saveAll(users);
        } catch (IOException e) {
            log.error("CSV file error");
            throw new ServiceException(ErrorCode.CSV_PARSE_ERROR);
        }
        return users.size();
    }

}
