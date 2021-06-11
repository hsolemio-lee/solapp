package com.sol.solapp.user.entity;

import com.sol.solapp.common.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    @Column(name = "password", length=200, nullable = false)
    private String password;

    @Column(name = "user_name", length=50, nullable = false)
    private String username;

    @Column(name = "first_name", length=50, nullable = true)
    private String firstName;

    @Column(name = "last_name", length=50, nullable = true)
    private String lastName;

    @Column(name = "email", length=100, nullable = false)
    private String email;

    @Column(name = "role", length=50, nullable = false)
    private String role;

    @Column(name = "smart_things_token", length=50, nullable = true)
    private String smartThingsToken;

    private String provider;

    private String providerId;

    @Builder
    public User(Date createDate, Date updateDate, Long id, String password, String username, String firstName, String lastName, String email, String role, String smartThingsToken, String provider, String providerId) {
        super(createDate, updateDate);
        this.id = id;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.smartThingsToken = smartThingsToken;
        this.provider = provider;
        this.providerId = providerId;
    }
}
