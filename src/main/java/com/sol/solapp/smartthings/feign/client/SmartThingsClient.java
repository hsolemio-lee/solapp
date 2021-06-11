package com.sol.solapp.smartthings.feign.client;

import com.sol.solapp.smartthings.feign.config.SmartThingsClientConfiguration;
import com.sol.solapp.smartthings.feign.dto.SceneDTO;
import com.sol.solapp.smartthings.feign.dto.SmartThingsResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "smartThingsClient", url = "https://api.smartthings.com", configuration = {SmartThingsClientConfiguration.class})
public interface SmartThingsClient {

    @GetMapping("/v1/scenes")
    SmartThingsResponseDTO<SceneDTO> getScenes();

    @PostMapping("/v1/scenes/{sceneId}/execute")
    SceneDTO executeScene(@PathVariable("sceneId") String sceneId);
}
