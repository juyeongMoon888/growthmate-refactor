package com.wanted.growthmate.payment.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "enrollment_transactions")
@DiscriminatorValue("ENROLLMENT_TRANSACTION")
public class EnrollmentTransaction extends PointTransaction {
    protected EnrollmentTransaction() {}
}
