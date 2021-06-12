package com.sol.solapp.postit.rest.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostitDTO {
    private Long id;
    private String textCntnt;
	private String createUser;
	private String updateUser;
}
