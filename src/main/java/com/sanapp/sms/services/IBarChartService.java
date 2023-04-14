package com.sanapp.sms.services;

import com.sanapp.sms.dto.ChartData;

import java.util.List;
import java.util.Map;

public interface IBarChartService {
    List<ChartData> yearByChartData(String year, String source);
}
