package com.utp.casa_europa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.utp.casa_europa.dtos.DashboardChartDto;
import com.utp.casa_europa.dtos.DashboardKpisDto;
import com.utp.casa_europa.services.DashboardService;

public class DashboardController {
     @Autowired
    private DashboardService dashboardService;

    @GetMapping("/kpis")
    public ResponseEntity<DashboardKpisDto> getKpis() {
        return ResponseEntity.ok(dashboardService.getKpis());
    }

    @GetMapping("/chart")
    public ResponseEntity<DashboardChartDto> getChart() {
        return ResponseEntity.ok(dashboardService.getChart());
    }
}
