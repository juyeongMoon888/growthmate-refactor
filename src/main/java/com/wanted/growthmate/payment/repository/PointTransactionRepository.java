package com.wanted.growthmate.payment.repository;

import com.wanted.growthmate.payment.domain.Point;
import com.wanted.growthmate.payment.domain.PointTransaction;
import com.wanted.growthmate.payment.dto.PointTransactionSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointTransactionRepository extends JpaRepository<PointTransaction, Long> {

    @Query("SELECT new com.wanted.growthmate.payment.dto.PointTransactionSummary(" +
            "pt.createdAt, pt.description, pt.amount, pt.balanceAfter) " +
            "FROM PointTransaction pt " +
            "WHERE pt.point = :point " +
            "ORDER BY pt.createdAt DESC")
    List<PointTransactionSummary> findSummariesByPoint(@Param("point") Point point);
}
