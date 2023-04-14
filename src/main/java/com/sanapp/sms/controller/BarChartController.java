package com.sanapp.sms.controller;

import com.sanapp.sms.dto.ChartData;
import com.sanapp.sms.services.IBarChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BarChartController {

    @Autowired
    private IBarChartService iBarChartService;

    @GetMapping("/chartdata/{year}")
    public ResponseEntity<List<ChartData>> chartData(@PathVariable("year") String year, @RequestParam(required = false, name = "source" ) String type) throws Exception {
        if (StringUtils.isEmpty(year)||StringUtils.isEmpty(type)) {
            throw new Exception("These fields should not blank");
        }
        return new ResponseEntity<>(iBarChartService.yearByChartData(year, type), HttpStatus.OK);
    }
}
