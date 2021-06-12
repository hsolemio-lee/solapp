package com.sol.solapp.postit.entity.converter;

import com.sol.solapp.common.mapper.EntityConverter;
import com.sol.solapp.postit.entity.Postit;
import com.sol.solapp.postit.rest.dto.PostitDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostitConverter extends EntityConverter<PostitDTO, Postit> {

    PostitConverter INSTANCE = Mappers.getMapper(PostitConverter.class);

}
