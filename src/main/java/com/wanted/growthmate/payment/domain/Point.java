package com.wanted.growthmate.payment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "points")
@Comment("원장 - 사용자별 현재 포인트 보유량")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: 나중에 주석해제
    //@OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "user_id", unique = true)
    //private User user;

    @Column(name = "balance", nullable = false, columnDefinition = "0")
    @Comment("현재 포인트 잔액")
    private Integer balance;
}
