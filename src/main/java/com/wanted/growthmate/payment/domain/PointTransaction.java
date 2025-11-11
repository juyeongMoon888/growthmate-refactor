package com.wanted.growthmate.payment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="dtype")
@Table(name = "point_transactions")
@Comment("포인트 거래 내역 - 모든 포인트 변동 이력 추적 (감사 추적용)")
@Getter
public class PointTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_transaction_id")
    @Comment("포인트 거래 고유 ID")
    private Long id;

    // TODO: Point랑 User 중 어디랑 연결되는가? 둘 다? Point랑만 연결되면 User는 간접적으로 알 수 있지 않나??
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "user_id", nullable = false)
    //@Comment("사용자 ID")
    //private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_id", nullable = false)
    @Comment("포인트")
    private Point point; // 어느 원장의 거래인지

    // FIXME: Enrollment랑 직접 연결하지 않고, EnrollmentTransaction 통해서 연결
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "enrollment_id", nullable = true)
    //@Comment("수강 ID")
    //private Enrollment enrollment;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    @Comment("거래 유형")
    private TransactionType transactionType;

    @Column(name = "amount", nullable = false)
    @Comment("거래 포인트 (양수: 적립, 음수: 차감)")
    private Integer amount; // 적립(+) / 차감(-) 금액

    @Column(name = "balance_after", nullable = false)
    @Comment("거래 후 잔액")
    private Integer balanceAfter;

    @Column(name = "description", nullable = false)
    @Comment("거래 설명")
    private String description;

    protected PointTransaction() {}
}
