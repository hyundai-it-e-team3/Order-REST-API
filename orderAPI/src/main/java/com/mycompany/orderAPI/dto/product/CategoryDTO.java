package com.mycompany.orderAPI.dto.product;

import lombok.Data;

@Data
public class CategoryDTO {
	private String categotyId;
	private String parentCategoryId;
	private String name;
	private int level;
}
