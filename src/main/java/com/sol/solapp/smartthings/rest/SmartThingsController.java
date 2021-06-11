package com.sol.solapp.smartthings.rest;

import com.sol.solapp.common.auth.PrincipalDetails;
import com.sol.solapp.smartthings.feign.client.SmartThingsClient;
import com.sol.solapp.smartthings.feign.dto.SceneDTO;
import com.sol.solapp.smartthings.feign.dto.SmartThingsResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/rest/v1/smartthings")
public class SmartThingsController {

    private final SmartThingsClient smartThingsClient;

    @GetMapping("/scenes")
    public ResponseEntity<SmartThingsResponseDTO<SceneDTO>> getScenes() {
        SmartThingsResponseDTO<SceneDTO> res = smartThingsClient.getScenes();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/scenes/execute/{sceneId}")
    public ResponseEntity<SceneDTO> getScenes(@PathVariable("sceneId")String sceneId) {
        SceneDTO res = smartThingsClient.executeScene(sceneId);
        return ResponseEntity.ok(res);
    }

}
