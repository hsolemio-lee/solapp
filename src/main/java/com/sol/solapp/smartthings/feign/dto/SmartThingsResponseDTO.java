package com.sol.solapp.smartthings.feign.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class SmartThingsResponseDTO<T> {
    List<T> items;
}
