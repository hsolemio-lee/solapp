package com.sol.solapp.user.entity;

import com.sol.solapp.common.entity.BaseEntity;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="com.sol.solapp.common.util.IdGenerator")
    private Long id;

    @Column(name = "first_name", length=50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length=50, nullable = false)
    private String lastName;

    @Column(name = "email", length=100, nullable = false)
    private String email;

    @Builder
    public User(Date createdTimestamp, Date lastModifiedTimestamp, Long id, String firstName, String lastName, String email) {
        super(createdTimestamp, lastModifiedTimestamp);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
