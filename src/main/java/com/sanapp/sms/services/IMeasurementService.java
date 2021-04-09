package com.sanapp.sms.services;

import com.sanapp.sms.dto.Measurement;

import java.util.List;

public interface IMeasurementService {

    String getName(String code);
    List<Measurement> listAllMeasurements();
}
