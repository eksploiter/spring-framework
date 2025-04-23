package com.ssafy.shopApp.model.dto;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
