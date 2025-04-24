package com.ssafy.shopApp.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private int productId;
	private String name;
	private String category;
	private String brand;
	private int price;
	private int stockQuantity;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Builder.Default
	private List<GiftProductDTO> gifts = new ArrayList<>();
	
}
