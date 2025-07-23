package com.utp.casa_europa.dtos;

import lombok.Data;
import java.util.List;

@Data
public class DashboardChartDto {
    private List<String> labels;
    private List<ChartDataset> datasets;

    @Data
    public static class ChartDataset {
        private String label;
        private List<Integer> data;
        private String backgroundColor;
    }
}