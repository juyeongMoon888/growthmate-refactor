package com.wanted.growthmate.payment.repository;

import com.wanted.growthmate.payment.domain.PointTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointTransactionRepository extends JpaRepository<PointTransaction, Long> {
    List<PointTransaction> findByPointUserIdOrderByIdDesc(Long userId);
}
