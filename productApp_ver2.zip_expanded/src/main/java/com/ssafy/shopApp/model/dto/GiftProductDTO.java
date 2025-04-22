package com.ssafy.shopApp.model.dto;

public class GiftProductDTO {
	private int giftId;
	private String name;
	private int productId;

	public GiftProductDTO(String name) {
		super();
		this.name = name;
	}

	public GiftProductDTO(int giftId, String name, int productId) {
		super();
		this.giftId = giftId;
		this.name = name;
		this.productId = productId;
	}

	public int getGiftId() {
		return giftId;
	}

	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "GiftProductDTO [giftId=" + giftId + ", name=" + name + ", productId=" + productId + "]";
	}

}
