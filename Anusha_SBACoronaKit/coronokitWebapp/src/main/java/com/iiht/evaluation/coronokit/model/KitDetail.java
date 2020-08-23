package com.iiht.evaluation.coronokit.model;

public class KitDetail {

	private int id;
	private int coronaKitId;
	private int productId;
	private int quantity;
	private double amount;
	
	public KitDetail() {
		// TODO Auto-generated constructor stub
	}
	
	public KitDetail(int id, int coronaKitId, int productId, int quantity, int amount) {
		this.id = id;
		this.coronaKitId = coronaKitId;
		this.productId = productId;
		this.quantity = quantity;
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCoronaKitId() {
		return coronaKitId;
	}
	public void setCoronaKitId(int coronaKitId) {
		this.coronaKitId = coronaKitId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "KitDetail [id=" + id + ", coronaKitId=" + coronaKitId + ", productId=" + productId + ", quantity=" + quantity + ", amount=" + amount + "]";
	}
	
	
}
