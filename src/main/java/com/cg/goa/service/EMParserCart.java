package com.cg.goa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.goa.dao.ICartRepository;
import com.cg.goa.entity.CartItemEntity;
import com.cg.goa.model.CartItemModel;

@Service
public class EMParserCart {

	@Autowired
	private ICartRepository cartrepo;
	
	public CartItemEntity parse(CartItemModel source) {
		return source == null ? null
				: new CartItemEntity(source.getCartId(),
						source.getUserId(),
						source.getCartTotalPrice(),
						source.getTotalQuantity()
						);

	}

	
	public CartItemModel parse(CartItemEntity source) {
		return source == null ? null
				: new CartItemModel(source.getCartId(),
						source.getUserId(),
						source.getCartTotalPrice(),
						source.getTotalQuantity()
						);

	}

}
