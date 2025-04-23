package com.ssafy.shopApp.model.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
//@ToString
//@Component

@Data
@Builder
@NoArgsConstructor
public class ProductDTO {
	private int productId;
	private String name;
	private String category;
	private String brand;
	private int price;
	private int stockQuantity;
	
	@Builder
	public ProductDTO(int productId, String name, String category, String brand, int price, int stockQuantity) {
		this(name, category, brand, price, stockQuantity);
		this.productId = productId;
	}
	
	@Builder
	public ProductDTO(String name, String category, String brand, int price, int stockQuantity) {
		super();
		this.name = name;
		this.category = category;
		this.brand = brand;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}
	
//	@Builder
//	public ProductDTO(int productId, String name, String category, String brand, int price, int stockQuantity) {
//		this(name, category, brand, price, stockQuantity);
//		this.productId = productId;
//	}


}
