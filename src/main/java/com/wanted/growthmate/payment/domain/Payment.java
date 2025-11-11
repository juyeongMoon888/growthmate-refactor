package com.wanted.growthmate.payment.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "payments")
@Comment("결제 - 포인트 결제 및 환불 내역")
@DiscriminatorValue("PAYMENT")
public class Payment extends PointTransaction {
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    @Comment("결제 수단")
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    @Comment("결제 유형(결제, 환불)")
    private PaymentType paymentType;

    @Column(name = "amount", nullable = false)
    @Comment("실제 결제 금액 (원)")
    private Integer amount;

    // NOTE: [1차 구현] amount:point_amount = 1:1
    @Column(name = "point_amount", nullable = false)
    @Comment("충전될 포인트 금액 (P)")
    private Integer pointAmount;

    // TODO: 향후 결제 대행사, 결제 상태, 결제 완료/취소 일시 등의 필드 추가 가능

    protected Payment() {}
}
