package com.sol.solapp.user.entity.converter;

import com.sol.solapp.common.mapper.EntityConverter;
import com.sol.solapp.user.entity.User;
import com.sol.solapp.user.rest.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter extends EntityConverter<UserDTO, User> {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mapping(target = "createDate", expression = "java(this.getCurrentInstant())")
    @Mapping(target = "updateDate", expression = "java(this.getCurrentInstant())")
    User toEntity(UserDTO dto);
}
