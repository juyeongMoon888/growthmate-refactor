package com.wanted.growthmate.payment.domain;

import com.wanted.growthmate.common.entity.BaseTimeEntity;
import com.wanted.growthmate.payment.exception.InvalidPointAmountException;
import com.wanted.growthmate.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Table(name = "points")
@Comment("원장 - 사용자별 현재 포인트 보유량")
public class Point extends BaseTimeEntity {
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "balance", nullable = false)
    @Comment("현재 포인트 잔액")
    private Integer balance;

    protected Point() {}

    public Point(User user, Integer balance) {
        this.user = user;
        this.balance = balance;
    }

    /*
     * 포인트 적립
     */
    public void addBalance(int amount) {
        if (amount <= 0) {
            throw new InvalidPointAmountException(amount);
        }
        this.balance += amount;
    }

    // TODO: 포인트 차감
}
