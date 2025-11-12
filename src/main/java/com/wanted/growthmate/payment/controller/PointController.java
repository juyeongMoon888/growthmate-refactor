package com.wanted.growthmate.payment.controller;

import com.wanted.growthmate.payment.domain.Point;
import com.wanted.growthmate.payment.domain.PointTransaction;
import com.wanted.growthmate.payment.dto.PointChargeRequest;
import com.wanted.growthmate.payment.dto.PointTransactionSummary;
import com.wanted.growthmate.payment.service.PointService;
import com.wanted.growthmate.payment.service.PointTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/points")
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;
    private final PointTransactionService pointTransactionService;

    @GetMapping
    public String getPointTransactionsPage(Model model){
        // TODO: 임시 사용자 ID -> @Login 으로 대체
        Long userId =  1L;

        // 포인트 잔액 조회 (없으면 생성)
        Point point = pointService.getOrCreatePoint(userId);

        // 포인트 내역 조회
        List<PointTransactionSummary> pointTransactions = pointTransactionService.getTransactionSummaries(point);

        // 모델에 데이터 전달
        model.addAttribute("currentPoints", point.getBalance());
        model.addAttribute("pointTransactions", pointTransactions);

        // 페이지 렌더링
        return "points/transactions";
    }

    @GetMapping("/charge")
    public String chargePage(){
        return "points/charge";
    }

    @PostMapping("/charge")
    public String chargePoints(@ModelAttribute PointChargeRequest dto, Model model) {
        // TODO: 실제 로그인 사용자로 변경 예정
        Long userId =  1L;

        //pointService.chargePoints(userId, dto.getAmount(), dto.getPaymentMethod());

        // 충전 완료 후 포인트 내역 페이지로 리다이렉트
        return "redirect:/points";
    }
}
