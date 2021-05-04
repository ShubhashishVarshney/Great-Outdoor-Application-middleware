package com.cg.goa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.goa.dao.IOrderRepository;
import com.cg.goa.entity.OrderEntity;
import com.cg.goa.model.OrderModel;


@Service
public class EMParserOrder {

	@Autowired
	private IOrderRepository orderrepo;
	
	
	public OrderEntity parse(OrderModel source) {
		return source == null ? null
				: new OrderEntity(source.getOrderId(),
						source.getUserId(),
						source.getTotalPrice(),
						source.getTotalQuantity(),
						source.getDispatchDate(),
						source.getDeliveryDate()
						);
	}


	public OrderModel parse(OrderEntity source) {
		return source == null ? null
				: new OrderModel(source.getOrderId(),
						source.getUserId(),
						source.getTotalPrice(),
						source.getTotalQuantity(),
						source.getDispatchDate(),
						source.getDeliveryDate()
						);
	}


}
