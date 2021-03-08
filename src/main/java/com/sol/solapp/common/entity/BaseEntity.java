package com.sol.solapp.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.time.Instant;

import static javax.persistence.TemporalType.TIMESTAMP;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {

    @CreatedDate
    @Column(name = "create_date", nullable = false, updatable = false, columnDefinition = "DATETIME(3)")
    @Temporal(TIMESTAMP)
    protected Instant createdTimestamp;

    @LastModifiedDate
    @Column(name = "update_date", nullable = false, columnDefinition = "DATETIME(3)")
    @Temporal(TIMESTAMP)
    protected Instant lastModifiedTimestamp;
}
