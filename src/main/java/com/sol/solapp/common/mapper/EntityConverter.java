package com.sol.solapp.common.mapper;

import java.time.Instant;
import java.util.List;

public interface EntityConverter <D,E> {
    E toEntity(D dto);
    D toDto(E entity);
    List<E> toEntity(List<D> dtos);
    List<D> toDto(List<E> entities);

    default Instant getCurrentInstant() {
        return Instant.now();
    }
}
