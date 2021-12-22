package com.mycompany.orderAPI.dao.productDB;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.orderAPI.dto.product.ProductDTO;

@Mapper
public interface ProductDAO {
	void updatePurchaseAmount(ProductDTO productDTO);
}
