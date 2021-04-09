package com.sanapp.sms.controller;

import com.sanapp.sms.services.IMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/measurement")
public class MeasurementRestController {

    @Autowired
    private IMeasurementService measurementService;
    @GetMapping("/id")
    private String getName(@RequestParam("code") String code){

        return measurementService.getName(code);
    }
}
