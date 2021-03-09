package com.sol.solapp.user.service.impl;

import com.sol.solapp.common.exception.ServiceException;
import com.sol.solapp.common.exception.code.ErrorCode;
import com.sol.solapp.user.entity.User;
import com.sol.solapp.user.entity.converter.UserConverter;
import com.sol.solapp.user.repository.UserRepository;
import com.sol.solapp.user.rest.dto.UserDTO;
import com.sol.solapp.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO dto) {
        User user = UserConverter.INSTANCE.toEntity(dto);
        userRepository.save(user);
        return UserConverter.INSTANCE.toDto(user);
    }
}
