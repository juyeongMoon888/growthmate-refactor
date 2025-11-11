package com.wanted.growthmate.payment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Table(name = "point_transactions")
@Comment("포인트 거래 내역")
public class PointTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_id")
    private Point point; // 어느 원장의 거래인지

    // TODO: 나중에 주석해제
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "enrollment_id")
    //private Enrollment enrollment;

    private Integer amount; // 적립(+) / 차감(-)

    @Enumerated(EnumType.STRING)
    private PointType pointType;

    protected PointTransaction() {}

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                //", user=" + user +
                //", enrollment=" + enrollment +
                ", amount=" + amount +
                ", pointType=" + pointType +
                '}';
    }
}
