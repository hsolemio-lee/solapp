package com.sol.solapp.user.rest;

import com.sol.solapp.user.rest.dto.UserDTO;
import com.sol.solapp.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1/users")
@AllArgsConstructor
@Slf4j
@Api(value="rest/v1/users", consumes = "application/json")
public class UserController {

    private final UserService userService;

    @ApiOperation(httpMethod = "POST", value = "user 추가", produces = "application/json", consumes = "application/json")
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) {
        log.debug("[POST] /users #Create new user", "/rest/v1");
        return ResponseEntity.ok(userService.createUser(dto));
    }

}
