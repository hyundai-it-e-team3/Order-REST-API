package com.mycompany.orderAPI.dao.productDB;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.orderAPI.dto.product.StockDTO;

@Mapper
public interface StockDAO {
	public StockDTO selectByStockDTO(StockDTO stockDTO);
	public List<StockDTO> selectByProductDetailId(String productDetailId);
	public int updatePlusByStockDTO(StockDTO stockDTO);
	public int updateMinusByStockDTO(StockDTO stockDTO);
}