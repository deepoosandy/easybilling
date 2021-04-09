package com.sanapp.sms.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the measurement_master database table.
 * 
 */
@Entity
@Table(name="measurement_master")
@NamedQuery(name="MeasurementMaster.findAll", query="SELECT m FROM MeasurementMaster m")
public class MeasurementMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name="calculation_formula")
	private String calculationFormula;

	@Column(name="measuement_unit_consist_of")
	private BigInteger measuementUnitConsistOf;

	@Column(name="measurement_code")
	private String measurementCode;

	@Column(name="measurement_desc")
	private String measurementDesc;

	@Column(name="measurement_name")
	private String measurementName;

	public MeasurementMaster() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCalculationFormula() {
		return this.calculationFormula;
	}

	public void setCalculationFormula(String calculationFormula) {
		this.calculationFormula = calculationFormula;
	}

	public BigInteger getMeasuementUnitConsistOf() {
		return this.measuementUnitConsistOf;
	}

	public void setMeasuementUnitConsistOf(BigInteger measuementUnitConsistOf) {
		this.measuementUnitConsistOf = measuementUnitConsistOf;
	}

	public String getMeasurementCode() {
		return this.measurementCode;
	}

	public void setMeasurementCode(String measurementCode) {
		this.measurementCode = measurementCode;
	}

	public String getMeasurementDesc() {
		return this.measurementDesc;
	}

	public void setMeasurementDesc(String measurementDesc) {
		this.measurementDesc = measurementDesc;
	}

	public String getMeasurementName() {
		return this.measurementName;
	}

	public void setMeasurementName(String measurementName) {
		this.measurementName = measurementName;
	}

}