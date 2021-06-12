package com.sol.solapp.postit.rest;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.lang.Long;
import com.sol.solapp.postit.rest.dto.PostitDTO;
import com.sol.solapp.postit.service.PostitService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.sol.solapp.common.auth.PrincipalDetails;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(value = "/rest/v1/postit", consumes = "application/json")
@RequestMapping("/rest/v1/postit")
public class PostitController {

	private final PostitService postitService;	

	@GetMapping
	public ResponseEntity<?> getPostits() {
		return ResponseEntity.ok(null);
	}

	@PostMapping
	public ResponseEntity<PostitDTO> createPostit(@AuthenticationPrincipal PrincipalDetails principal, @RequestBody PostitDTO postit) {
		postit.setCreateUser(principal.getUser().getUsername());
		postit.setUpdateUser(principal.getUser().getUsername());	
		return ResponseEntity.ok(postitService.createPostit(postit));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatePostit(@PathVariable("id") Long id) {
		return ResponseEntity.ok(null);
	}

		

}
