package com.mycompany.orderAPI.dto.product;

import lombok.Data;

@Data
public class StockDTO {
	private String psize;
	private int amount;
	private String productDetailId;
}
