package com.ssafy.shopApp.model.dto;

/*
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    brand VARCHAR(50),
    price DECIMAL(10,2) NOT NULL,
    stock_quantity INT NOT NULL
 */
import java.util.*;

public class ProductDTO {
	private int productId;
	private String name;
	private String category;
	private String brand;
	private int price;
	private int stockQuantity;

	private List<GiftProductDTO> gifts;

	public ProductDTO(String name, String category, String brand, int price, int stockQuantity) {
		super();
		this.name = name;
		this.category = category;
		this.brand = brand;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	public ProductDTO(int productId, String name, String category, String brand, int price, int stockQuantity) {
		this(name, category, brand, price, stockQuantity);
		this.productId = productId;
	}

	public ProductDTO() {
		super();
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public List<GiftProductDTO> getGifts() {
		return gifts;
	}

	public void setGifts(List<GiftProductDTO> gifts) {
		this.gifts = gifts;
	}

	@Override
	public String toString() {
		return "ProductDTO [productId=" + productId + ", name=" + name + ", category=" + category + ", brand=" + brand
				+ ", price=" + price + ", stockQuantity=" + stockQuantity + ", gifts=" + gifts + "]";
	}

}
