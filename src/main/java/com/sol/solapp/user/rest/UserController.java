package com.sol.solapp.user.rest;

import com.sol.solapp.user.rest.dto.InsertUserReportDTO;
import com.sol.solapp.user.rest.dto.UserDTO;
import com.sol.solapp.user.service.UserService;
import com.sol.solapp.user.validator.UserValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/rest/v1/users")
@AllArgsConstructor
@Slf4j
@Api(value="rest/v1/users", consumes = "application/json")
public class UserController {

    private final UserService userService;


    @InitBinder("userDTO")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }

    @ApiOperation(httpMethod = "POST", value = "user 추가", produces = "application/json", consumes = "application/json")
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        log.debug("[POST] /users #Create new user");
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @ApiOperation(httpMethod = "POST", value = "user csv 업로드", produces = "application/json", consumes = "application/json")
    @PostMapping("/upload")
    public ResponseEntity<InsertUserReportDTO> uploadUsers(@RequestParam("file") MultipartFile file) {
        log.debug("[POST] /users/upload # Create new users");
        return ResponseEntity.ok(userService.createUsers(file));
    }

    @ApiOperation(httpMethod = "GET", value = "user 조회", produces = "application/json", consumes = "application/json")
    @GetMapping
    public ResponseEntity<Page<UserDTO>> getUsers(Pageable pageable) {
        log.debug("[GET] /users # Get Users");
        return ResponseEntity.ok(userService.getUsers(pageable));
    }


}
