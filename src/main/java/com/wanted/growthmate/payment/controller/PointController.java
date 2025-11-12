package com.wanted.growthmate.payment.controller;

import com.wanted.growthmate.payment.domain.Point;
import com.wanted.growthmate.payment.service.PointService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/points")
public class PointController {
    private final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping
    public String getPointTransactions(Model model){
        // 임시 사용자 ID -> 어디서 받아와야 하나?
        Long userId =  1L;

        // 포인트 잔액 조회
//        Point point = pointService.getCurrentPoint(userId);

        // TODO: 포인트 내역 조회 -> service 호출 (충전 기능 구현 후 추가)

        // 모델에 데이터 전달
//        model.addAttribute("currentPoints", point.getBalance());
        //model.addAttribute("pointTransactions", pointTransactions);

        // 페이지 렌더링
        return "points/transactions";
    }

    @GetMapping("/charge")
    public String chargePage(){
        return "points/charge";
    }
}
