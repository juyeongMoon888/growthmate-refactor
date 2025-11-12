package com.wanted.growthmate.payment.service;

import com.wanted.growthmate.payment.domain.Point;
import com.wanted.growthmate.payment.domain.PointTransaction;
import com.wanted.growthmate.payment.dto.PointTransactionSummary;

import java.util.List;

public interface PointTransactionService {
    List<PointTransactionSummary> getTransactionSummaries(Point point);
}
