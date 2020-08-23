package com.iiht.evaluation.coronokit.model;

public class CoronaKit {
	
	private int id;
	private String personName;
	private String email;
	private String contactNumber;
	private double totalAmount;
	private String deliveryAddress;
	private String orderDate;
	private boolean orderFinalized;
	
	public CoronaKit() {
		// TODO Auto-generated constructor stub
	}
	
	public CoronaKit(int id, String personName, String email, String contactNumber, int totalAmount,
			String deliveryAddress, String orderDate, boolean orderFinalized) {
		this.id = id;
		this.personName = personName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.totalAmount = totalAmount;
		this.deliveryAddress = deliveryAddress;
		this.orderDate = orderDate;
		this.orderFinalized = orderFinalized;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public boolean isOrderFinalized() {
		return orderFinalized;
	}
	public void setOrderFinalized(boolean orderFinalized) {
		this.orderFinalized = orderFinalized;
	}
	
	
	

}
