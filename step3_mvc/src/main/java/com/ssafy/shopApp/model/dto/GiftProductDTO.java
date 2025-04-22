package com.ssafy.shopApp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

//@Getter
//@Setter
//@ToString
//@Component

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class GiftProductDTO {
	private int giftId;
	private @NonNull String name;
	private int productId;
}
