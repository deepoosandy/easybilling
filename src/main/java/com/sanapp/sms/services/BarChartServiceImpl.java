package com.sanapp.sms.services;

import com.sanapp.sms.domain.CementChartDomain;
import com.sanapp.sms.domain.ChartDomain;
import com.sanapp.sms.domain.ExpenseChartDomain;
import com.sanapp.sms.domain.MistriChartDomain;
import com.sanapp.sms.dto.ChartData;
import com.sanapp.sms.repository.CementChartRepository;
import com.sanapp.sms.repository.ExpenseChartRepository;
import com.sanapp.sms.repository.MistriChartRepository;
import com.sanapp.sms.utils.IDataCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BarChartServiceImpl implements IBarChartService {

    @Autowired
    private MistriChartRepository mistriChartRepository;

    @Autowired
    private ExpenseChartRepository expenseChartRepository;

    @Autowired
    private CementChartRepository cementChartRepository;


    @Override
    public List<ChartData> yearByChartData(String year, String source) {
        List<ChartData> dataList = null;

        switch (source) {
            case "E":
                dataList = IDataCalculator.dataPopulation(expenseChartRepository.expenseData(year));
                break;
            case "M":
                dataList = IDataCalculator.dataPopulation(mistriChartRepository.mistriData(year));
                break;

            case "C":
                dataList = IDataCalculator.dataPopulation(cementChartRepository.cementData(year));
                break;

            case "T":
                List<Map<Month, Double>> allTotalDataMap = new ArrayList<>();
                allTotalDataMap.add(IDataCalculator.dataPopulateInMap(mistriChartRepository.mistriData(year)));
                allTotalDataMap.add(IDataCalculator.dataPopulateInMap(expenseChartRepository.expenseData(year)));
                allTotalDataMap.add(IDataCalculator.dataPopulateInMap(cementChartRepository.cementData(year)));
                dataList = IDataCalculator.dataPopulationFromDataMap(allTotalDataMap);
                break;
            default:
                System.out.println("Nothing has sent");
        }
       // dataList = dataList.stream().sorted(Comparator.comparing(ChartData::getMonth)).collect(Collectors.toList());
        return addRemainingMonths( dataList);

    }

    private List<ChartData> addRemainingMonths(List<ChartData> dataList) {
        List<ChartData> chartDataListWithoutAmount =
                IntStream.range(1, 13).boxed().map(i -> {
                    {
                        ChartData cd = ChartData.builder().month(Month.of(i))
                                .amount(Double.valueOf(0)).build();
                        if (dataList.contains(cd)) {
                            int index = dataList.indexOf(cd);
                            cd = dataList.get(index);
                        }
                        return cd;
                    }
                }).collect(Collectors.toList());

        return chartDataListWithoutAmount;
    }
}
