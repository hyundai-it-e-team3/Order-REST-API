package com.mycompany.orderAPI.dto.product;

import lombok.Data;

@Data
public class ProductDetailDTO {
	private String productDetailId;
	private String withProduct;
	private String colorCode;
	private String colorChip;
	private String productId;
}
