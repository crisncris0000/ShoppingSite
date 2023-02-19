package com.springboot.ShoppingSite.Entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "confirmation_token")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long tokenId;

    @Column(name = "token")
    private String confirmationToken;

    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    public ConfirmationToken(User user){
        this.user = user;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

}
