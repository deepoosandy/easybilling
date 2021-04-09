package com.sanapp.sms.repository;

import com.sanapp.sms.domain.MeasurementMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMeasurementRepository extends JpaRepository<MeasurementMaster, Long> {
    MeasurementMaster findByMeasurementCode(String measurementCode);
}
