package com.sanapp.sms.utils;

import com.sanapp.sms.domain.ChartDomain;
import com.sanapp.sms.dto.ChartData;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface IDataCalculator {
    static <T extends ChartDomain> Map<Month, Double> dataPopulateInMap(List<T> chartDomains) {
        return chartDomains.stream().collect(Collectors.toMap(cd -> cd.getDate().getMonth(), x -> x.getAmount(), (m, n) ->
                m + n));
    }

    static <T extends ChartDomain> List<ChartData> dataPopulation(List<T> chartDomains) {
        Map<Month, Double> dataMap = chartDomains.stream().collect(Collectors.toMap(cd -> cd.getDate().getMonth(), x -> x.getAmount(), (m, n) ->
                m + n));
        return dataMap.entrySet().stream().map(e -> ChartData.builder().month(e.getKey()).amount(e.getValue()).build()).collect(Collectors.toList());
    }

    static List<ChartData> dataPopulationFromDataMap(List<Map<Month, Double>> listOfdataMap) {
        return listOfdataMap.stream().flatMap(e -> e.entrySet().stream()).
                collect(Collectors.toMap(x -> x.getKey(), p -> p.getValue(), (m, n) -> m + n)).entrySet().stream()
                .map(e -> ChartData.builder().month(e.getKey()).amount(e.getValue()).build()).collect(Collectors.toList());
    }
}
