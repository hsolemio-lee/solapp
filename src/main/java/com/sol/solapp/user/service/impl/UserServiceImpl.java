package com.sol.solapp.user.service.impl;

import com.sol.solapp.common.exception.ServiceException;
import com.sol.solapp.common.exception.code.ErrorCode;
import com.sol.solapp.common.util.CSVUtil;
import com.sol.solapp.common.util.ValidateUtil;
import com.sol.solapp.user.entity.User;
import com.sol.solapp.user.entity.converter.UserConverter;
import com.sol.solapp.user.repository.UserRepository;
import com.sol.solapp.user.rest.dto.InsertUserReportDTO;
import com.sol.solapp.user.rest.dto.UserDTO;
import com.sol.solapp.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final String[] HEADER = { "id", "firstname", "lastname", "email" };

    @Override
    public UserDTO createUser(UserDTO dto) {
        User user = UserConverter.INSTANCE.toEntity(dto);
        user = userRepository.save(user);
        return UserConverter.INSTANCE.toDto(user);
    }

    @Override
    public InsertUserReportDTO createUsers(MultipartFile file) {
        if(!CSVUtil.hasCSVFormat(file)) {
            throw new ServiceException(ErrorCode.INVALID_CSV_FILE);
        }
        Map<Boolean, List<User>> partitionedUsers;

        try {
            partitionedUsers = CSVUtil.convertCsvToEntity(file.getInputStream(), convertUser, validationRule);
        } catch (IOException e) {
            log.error("CSV file error");
            throw new ServiceException(ErrorCode.CSV_PARSE_ERROR);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(ErrorCode.UNKNOWN_ERROR);
        }
        List<User> users = partitionedUsers.get(true);
        users = userRepository.saveAll(users);
        userRepository.flush();
        long updatedCount = users.stream().filter(user -> null == user.getCreateDate()).count();
        long failedCount = partitionedUsers.get(false).size();
        long totalCount = partitionedUsers.get(true).size() + partitionedUsers.get(false).size();
        long insertedCount = totalCount - updatedCount - failedCount;
        return InsertUserReportDTO.builder()
                .updatedCount(updatedCount)
                .failedCount(failedCount)
                .insertedCount(insertedCount)
                .totalCount(totalCount)
                .build();
    }

    @Override
    public Page<UserDTO> getUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        long totalCount = users.getTotalElements();

        return new PageImpl<UserDTO>(users.getContent().stream().map(UserConverter.INSTANCE::toDto).collect(Collectors.toList()), pageable, totalCount);
    }

    private final Function<CSVRecord, User> convertUser = record -> User.builder()
            .id("".equals(record.get(HEADER[0])) ? 0L : Long.parseLong(record.get(HEADER[0])))
            .firstName(Optional.ofNullable(record.get(HEADER[1])).orElse(""))
            .lastName(Optional.ofNullable(record.get(HEADER[2])).orElse(""))
            .email(Optional.ofNullable(record.get(HEADER[3])).orElse(""))
            .build();

    private final Predicate<User> validationRule = user -> !user.getId().equals(0L)
            && !ValidateUtil.isEmpty(user.getFirstName())
            && !ValidateUtil.isEmpty(user.getLastName())
            && !ValidateUtil.isEmpty(user.getEmail())
            && ValidateUtil.isValidEmail(user.getEmail());
}
