package com.wanted.growthmate.payment.service;

import com.wanted.growthmate.payment.domain.Point;
import com.wanted.growthmate.payment.domain.PointTransaction;
import com.wanted.growthmate.payment.dto.PointTransactionSummary;
import com.wanted.growthmate.payment.repository.PointTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointTransactionServiceImpl implements PointTransactionService {
    private final PointTransactionRepository pointTransactionRepository;

    public List<PointTransactionSummary> getTransactionSummaries(Point point) {
        return pointTransactionRepository.findSummariesByPoint(point);
    }
}
