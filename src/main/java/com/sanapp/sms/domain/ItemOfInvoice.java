package com.sanapp.sms.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * The persistent class for the item_of_invoice database table.
 * 
 */
@Entity
@Table(name="item_of_invoice")
@NamedQuery(name="ItemOfInvoice.findAll", query="SELECT i FROM ItemOfInvoice i")
public class ItemOfInvoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name="item_code")
	private String itemCode;

	@Column(name="item_measurement_code")
	private String itemMeasurementCode;

	@Column(name="item_quantity")
	private int itemQuantity;

	@Column(name="item_unit_price")
	private double itemUnitPrice;

	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="selling_date")
	private LocalDateTime sellingDate;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	@JoinColumn(name="invocie_number")
	private Invoice invoice;

	public ItemOfInvoice() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemMeasurementCode() {
		return this.itemMeasurementCode;
	}

	public void setItemMeasurementCode(String itemMeasurementCode) {
		this.itemMeasurementCode = itemMeasurementCode;
	}

	public int getItemQuantity() {
		return this.itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public double getItemUnitPrice() {
		return this.itemUnitPrice;
	}

	public void setItemUnitPrice(double itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}

	public LocalDateTime getSellingDate() {
		return this.sellingDate;
	}

	public void setSellingDate(LocalDateTime sellingDate) {
		this.sellingDate = sellingDate;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}