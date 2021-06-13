package com.sol.solapp.postit.entity;

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
@Table(name = "Postit")
public class Postit extends BaseEntity {

    @Id
    @GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="com.sol.solapp.common.util.IdGenerator")
    private Long id;

    @Column(name = "text_cntnt", length=2000, nullable = true)
    private String textCntnt;

	@Column(name = "create_user", length=100, nullable = false)
	private String createUser;

	@Column(name = "update_user", length=100, nullable = false)
	private String updateUser;

    @Builder
    public Postit(Date createDate, Date updateDate, Long id, String textCntnt) {
        super(createDate, updateDate);
		this.id = id;
		this.textCntnt = textCntnt;
	}

}
