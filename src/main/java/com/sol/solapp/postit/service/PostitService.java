package com.sol.solapp.postit.service;

import com.sol.solapp.common.exception.ServiceException;
import com.sol.solapp.common.exception.code.ErrorCode;
import com.sol.solapp.common.util.ValidateUtil;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.sol.solapp.postit.rest.dto.PostitDTO;
import com.sol.solapp.postit.entity.Postit;
import com.sol.solapp.postit.entity.converter.PostitConverter;
import com.sol.solapp.postit.repository.PostitRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PostitService {

    private final PostitRepository postitRepository;
 
    public PostitDTO createPostit(PostitDTO dto) {
        Postit postit = PostitConverter.INSTANCE.toEntity(dto);
        postit = postitRepository.save(postit);    
        return PostitConverter.INSTANCE.toDto(postit);
    }
	
	public Page<PostitDTO> getPostits(Pageable pageable) {
		Page<Postit> postits = postitRepository.findAllByOrderByUpdateDateDesc(pageable);
		long totalCount = postits.getTotalElements();
		return new PageImpl<PostitDTO>(postits.getContent().stream().map(PostitConverter.INSTANCE::toDto).collect(Collectors.toList()), pageable, totalCount);
	}

    public PostitDTO updatePostit(PostitDTO dto) {
        Postit postit = PostitConverter.INSTANCE.toEntity(dto);
        Postit originPostit = postitRepository.findById(postit.getId()).orElseThrow(() -> new ServiceException(ErrorCode.POSTIT_NOT_FOUND));
        postit.setCreateUser(originPostit.getCreateUser());
        postit = postitRepository.save(postit);
        return PostitConverter.INSTANCE.toDto(postit);
    }

}
