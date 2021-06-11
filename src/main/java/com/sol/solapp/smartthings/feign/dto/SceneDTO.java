package com.sol.solapp.smartthings.feign.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class SceneDTO {
    String sceneId;
    String sceneName;
    String locationId;
    String status;
}
