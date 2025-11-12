package com.wanted.growthmate.payment.domain;

import com.wanted.growthmate.user.entity.User;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "points")
@Comment("원장 - 사용자별 현재 포인트 보유량")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private Long id;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", unique = true)
//    private User user;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "balance", nullable = false)
    @Comment("현재 포인트 잔액")
    private Integer balance;

    protected Point() {}

    public Long getId() {
        return id;
    }

//    public User getUser() {
//        return user;
//    }

    public Integer getBalance() {
        return balance;
    }
}
