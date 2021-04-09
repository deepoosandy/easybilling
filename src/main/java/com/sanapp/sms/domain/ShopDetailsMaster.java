package com.sanapp.sms.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the shop_details_master database table.
 * 
 */
@Entity
@Table(name="shop_details_master")
@NamedQuery(name="ShopDetailsMaster.findAll", query="SELECT s FROM ShopDetailsMaster s")
public class ShopDetailsMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name="bank_account_name")
	private String bankAccountName;

	@Column(name="bank_account_number")
	private String bankAccountNumber;

	@Column(name="bank_ifsc_number")
	private String bankIfscNumber;

	private String district;

	@Column(name="phone_number_1")
	private String phoneNumber1;

	@Column(name="phone_number_2")
	private String phoneNumber2;

	private String pincode;

	@Column(name="shop_address_1")
	private String shopAddress1;

	@Column(name="shop_description")
	private String shopDescription;

	@Column(name="shop_gst_number")
	private String shopGstNumber;

	@Column(name="shop_name")
	private String shopName;

	@Column(name="state")
	private String state;

	@Column(name="invoice_prefix")
	private String invoicePrefix;

	public ShopDetailsMaster() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBankAccountName() {
		return this.bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountNumber() {
		return this.bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankIfscNumber() {
		return this.bankIfscNumber;
	}

	public void setBankIfscNumber(String bankIfscNumber) {
		this.bankIfscNumber = bankIfscNumber;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPhoneNumber1() {
		return this.phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public String getPhoneNumber2() {
		return this.phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	public String getPincode() {
		return this.pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getShopAddress1() {
		return this.shopAddress1;
	}

	public void setShopAddress1(String shopAddress1) {
		this.shopAddress1 = shopAddress1;
	}

	public String getShopDescription() {
		return this.shopDescription;
	}

	public void setShopDescription(String shopDescription) {
		this.shopDescription = shopDescription;
	}

	public String getShopGstNumber() {
		return this.shopGstNumber;
	}

	public void setShopGstNumber(String shopGstNumber) {
		this.shopGstNumber = shopGstNumber;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getInvoicePrefix() {
		return invoicePrefix;
	}

	public void setInvoicePrefix(String invoicePrefix) {
		this.invoicePrefix = invoicePrefix;
	}
}