package com.mycompany.orderAPI.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.orderAPI.dao.productDB.StockDAO;
import com.mycompany.orderAPI.dto.product.StockDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockService {
	
	@Resource
	private StockDAO stockDAO;
	
	public enum StockResult {
		SUCCESS,
		FAIL,
		FAIL_STOCK
	}
	
	public StockDTO getStock(StockDTO stockDTO) {
		log.info("실행");
		log.info(stockDTO.toString());
		return stockDAO.selectByStockDTO(stockDTO);
	}

	
	public StockResult addStock(StockDTO stockDTO) {
		StockDTO stk = stockDAO.selectByStockDTO(stockDTO);
		if(stk ==null) {
			return StockResult.FAIL;
		}
		stockDAO.updatePlusByStockDTO(stockDTO);
		return StockResult.SUCCESS;
	}
	
	public StockResult minusStock(StockDTO stockDTO) {
		StockDTO stk = stockDAO.selectByStockDTO(stockDTO);
		if(stk ==null) {
			return StockResult.FAIL;
		} else if(stk.getAmount() < stockDTO.getAmount()) {
			return StockResult.FAIL_STOCK;
		} 
		stockDAO.updateMinusByStockDTO(stockDTO);
		return StockResult.SUCCESS;
		
	}
}