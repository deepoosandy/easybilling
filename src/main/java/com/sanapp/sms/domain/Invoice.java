package com.sanapp.sms.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the invoice database table.
 * 
 */
@Entity
@NamedQuery(name="Invoice.findAll", query="SELECT i FROM Invoice i")
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Id
	@Column(name="invoice_number")
	private String invoiceNumber;

	@Column(name="bill_to")
	private String billTo;

	@Column(name="bill_total_amount")
	private String billTotalAmount;

	@Column(name="billed_item_count")
	private int billedItemCount;

	@Column(name="billing_address")
	private String billingAddress;

	@Column(name="billing_phone_number")
	private String billingPhoneNumber;

	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="invoice_date")
	private LocalDateTime invoiceDate;

	@Column(name="totol_invoice_generated")
	private int totolInvoiceGenerated;

	//bi-directional many-to-one association to ItemOfInvoice
	@OneToMany(mappedBy="invoice", cascade = CascadeType.ALL)
	private List<ItemOfInvoice> itemOfInvoices;

	public Invoice() {
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getBillTo() {
		return this.billTo;
	}

	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	public String getBillTotalAmount() {
		return this.billTotalAmount;
	}

	public void setBillTotalAmount(String billTotalAmount) {
		this.billTotalAmount = billTotalAmount;
	}

	public int getBilledItemCount() {
		return this.billedItemCount;
	}

	public void setBilledItemCount(int billedItemCount) {
		this.billedItemCount = billedItemCount;
	}

	public String getBillingAddress() {
		return this.billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getBillingPhoneNumber() {
		return this.billingPhoneNumber;
	}

	public void setBillingPhoneNumber(String billingPhoneNumber) {
		this.billingPhoneNumber = billingPhoneNumber;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(LocalDateTime invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public int getTotolInvoiceGenerated() {
		return this.totolInvoiceGenerated;
	}

	public void setTotolInvoiceGenerated(int totolInvoiceGenerated) {
		this.totolInvoiceGenerated = totolInvoiceGenerated;
	}

	public List<ItemOfInvoice> getItemOfInvoices() {
		return this.itemOfInvoices;
	}

	public void setItemOfInvoices(List<ItemOfInvoice> itemOfInvoices) {
		this.itemOfInvoices = itemOfInvoices;
	}

	public ItemOfInvoice addItemOfInvoice(ItemOfInvoice itemOfInvoice) {
		getItemOfInvoices().add(itemOfInvoice);
		itemOfInvoice.setInvoice(this);

		return itemOfInvoice;
	}

	public ItemOfInvoice removeItemOfInvoice(ItemOfInvoice itemOfInvoice) {
		getItemOfInvoices().remove(itemOfInvoice);
		itemOfInvoice.setInvoice(null);

		return itemOfInvoice;
	}

}