package com.sol.solapp.user.validator;

import com.sol.solapp.common.exception.code.ErrorCode;
import com.sol.solapp.common.util.ValidateUtil;
import com.sol.solapp.user.rest.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
@Slf4j
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO dto = (UserDTO) target;

        if(ValidateUtil.isEmpty(dto.getFirstName())) {
            log.error("invalid param : firstName");
            errors.rejectValue("firstName", ErrorCode.PARAMETER_EMPTY.getCode());
        }

        if(ValidateUtil.isEmpty(dto.getLastName())) {
            log.error("invalid param : lastName");
            errors.rejectValue("lastName", ErrorCode.PARAMETER_EMPTY.getCode());
        }

        if(ValidateUtil.isEmpty(dto.getEmail())) {
            log.error("invalid param : email");
            errors.rejectValue("email", ErrorCode.PARAMETER_EMPTY.getCode());
        }

        if(!ValidateUtil.isValidEmail(dto.getEmail())) {
            log.error("invalid param : email");
            errors.rejectValue("email", ErrorCode.PARAMETER_INVALID.getCode());
        }
    }
}
