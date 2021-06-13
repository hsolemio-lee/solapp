package com.sol.solapp.postit.rest.dto;

import lombok.*;
import java.util.Date;

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
	private Date createDate;
	private Date updateDate;
}
