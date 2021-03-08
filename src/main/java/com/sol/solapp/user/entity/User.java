package com.sol.solapp.user.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User{

    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private Instant createDate;
    private Instant updateDate;
}
