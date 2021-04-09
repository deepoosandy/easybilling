package com.sanapp.sms.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the item_details_master database table.
 * 
 */
@Entity
@Table(name="item_details_master")
@NamedQuery(name="ItemDetailsMaster.findAll", query="SELECT i FROM ItemDetailsMaster i")
public class ItemDetailsMaster implements Serializable {
	private static final long serialVersionUID = 1L;


	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Id
	@Column(name="item_code")
	private String itemCode;

	@Column(name="item_description")
	private String itemDescription;

	@Column(name="item_name")
	private String itemName;

	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="up_loaded_date")
	private LocalDateTime upLoadedDate;

	//bi-directional many-to-one association to UnitPriceT
	@OneToMany(mappedBy="itemDetailsMaster",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<UnitPriceT> unitPriceTs;

	public ItemDetailsMaster() {
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemDescription() {
		return this.itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public LocalDateTime getUpLoadedDate() {
		return this.upLoadedDate;
	}

	public void setUpLoadedDate(LocalDateTime upLoadedDate) {
		this.upLoadedDate = upLoadedDate;
	}

	public List<UnitPriceT> getUnitPriceTs() {
		return this.unitPriceTs;
	}

	public void setUnitPriceTs(List<UnitPriceT> unitPriceTs) {
		this.unitPriceTs = unitPriceTs;
	}

	public UnitPriceT addUnitPriceT(UnitPriceT unitPriceT) {
		getUnitPriceTs().add(unitPriceT);
		unitPriceT.setItemDetailsMaster(this);

		return unitPriceT;
	}

	public UnitPriceT removeUnitPriceT(UnitPriceT unitPriceT) {
		getUnitPriceTs().remove(unitPriceT);
		unitPriceT.setItemDetailsMaster(null);

		return unitPriceT;
	}

}