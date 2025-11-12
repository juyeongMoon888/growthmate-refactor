package com.wanted.growthmate.payment.domain;

import jakarta.persistence.*;

// FIXME: 임시 코드 (추후 삭제)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected User() {}

    public Long getId() { return id; }
}
