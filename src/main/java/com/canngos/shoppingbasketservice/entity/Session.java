package com.canngos.shoppingbasketservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class Session {

    @Id
    private String jwtToken;

    private String email;
    private Date tokenExpiredDate;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
