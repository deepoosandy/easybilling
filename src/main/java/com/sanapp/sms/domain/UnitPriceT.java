package com.sanapp.sms.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * The persistent class for the unit_price_t database table.
 * 
 */
@Entity
@Table(name="unit_price_t")
@NamedQuery(name="UnitPriceT.findAll", query="SELECT u FROM UnitPriceT u")
public class UnitPriceT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="eff_dt")
	private LocalDateTime effDt;

	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="thru_dt")
	private LocalDateTime thruDt;

	@Column(name="unit_price")
	private double unitPrice;

	//bi-directional many-to-one association to ItemDetailsMaster
	@ManyToOne
	@JoinColumn(name="item_code")
	private ItemDetailsMaster itemDetailsMaster;

	@Column(name="measurement_unit")
	private String measurementUnit;

	public UnitPriceT() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getEffDt() {
		return this.effDt;
	}

	public void setEffDt(LocalDateTime effDt) {
		this.effDt = effDt;
	}

	public LocalDateTime getThruDt() {
		return this.thruDt;
	}

	public void setThruDt(LocalDateTime thruDt) {
		this.thruDt = thruDt;
	}

	public double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public ItemDetailsMaster getItemDetailsMaster() {
		return this.itemDetailsMaster;
	}

	public void setItemDetailsMaster(ItemDetailsMaster itemDetailsMaster) {
		this.itemDetailsMaster = itemDetailsMaster;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
}