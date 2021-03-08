package com.sol.solapp.user.entity;

import com.sol.solapp.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    private Long id;

    @Column(name = "first_name", length=50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length=50, nullable = true)
    private String lastName;

    @Column(name = "email", length=100, nullable = true)
    private String email;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "update_date")
    private Instant updateDate;

    @Builder
    public User(Instant createdTimestamp, Instant lastModifiedTimestamp, Long id, String firstName, String lastName, String email, Instant createDate, Instant updateDate) {
        super(createdTimestamp, lastModifiedTimestamp);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
