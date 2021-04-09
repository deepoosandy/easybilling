package com.sanapp.sms.services;

import com.sanapp.sms.dto.Measurement;
import com.sanapp.sms.repository.IMeasurementRepository;
import com.sanapp.sms.utils.ItemDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeasurementServiceImpl implements IMeasurementService {
    @Autowired
    private IMeasurementRepository measurementRepository;
    @Override
    public String getName(String code) {
        return measurementRepository.findByMeasurementCode(code).getMeasurementName();
    }

    @Override
    public List<Measurement> listAllMeasurements() {

        return measurementRepository.findAll().stream().map(ItemDetailsMapper::domainToDto).collect(Collectors.toList());

    }
}
