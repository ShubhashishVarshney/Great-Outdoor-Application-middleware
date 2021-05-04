package com.cg.goa.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.goa.dao.IOrderRepository;
import com.cg.goa.exception.OrderException;
import com.cg.goa.model.OrderModel;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderRepository orderrepo;

	@Autowired
	private EMParserOrder parser;
	/*
	 * A default Constructor with no implementation
	 */

	public OrderServiceImpl() {

	}

	/*
	 * Corresponding Getters and Setters for private members
	 * 
	 */
	public OrderServiceImpl(IOrderRepository orderrepo) {
		super();
		this.orderrepo = orderrepo;
		this.parser = new EMParserOrder();
	}
	/*
	 * service implementation for Finding Order By UserId
	 */
	@Transactional
	@Override
	public List<OrderModel> findOrdersByUserId(String userId) {

		List<OrderModel> p = orderrepo.findByuserId(userId).stream().map(parser::parse).collect(Collectors.toList());
		return p;
	}
	/*
	 * service implementation for Finding All Orders
	 */
	@Transactional
	@Override
	public List<OrderModel> findAllOrders() {

		return orderrepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}
	/*
	 * service implementation for Adding new Order
	 * @Throws OrderException
	 */
	@Transactional
	@Override
	public OrderModel addOrder(OrderModel orderEntity) throws OrderException {
		if (!orderrepo.existsById(orderEntity.getOrderId())) {
			parser.parse(orderrepo.save(parser.parse(orderEntity)));
		} else {
			throw new OrderException("already exist Found");
		}
		return orderEntity;
	}
	/*
	 * service implementation for deleting All Orders
	 * @Throws OrderException
	 */
	@Transactional
	@Override
	public boolean deleteAllOrders() throws OrderException {
		if (orderrepo.findAll().isEmpty()) {
			throw new OrderException("There are no orders to delete");
		}
		orderrepo.deleteAll();
		return true;
	}
	/*
	 * service implementation for deleting Order By Id
	 * @Throws OrderException
	 */
	@Transactional
	@Override
	public boolean deleteOrderById(Integer orderId) throws OrderException {
		if (orderrepo.existsById(orderId)) {
			orderrepo.deleteById(orderId);
			return true;
		} else {
			throw new OrderException("Order Id is Not found");
		}
	}
	/*
	 * service implementation for Updating Date
	 * @Throws OrderException
	 */
	@Transactional
	@Override
	public boolean updateDate(Integer orderId, LocalDateTime dispatchDate, LocalDateTime arrivalDate)
			throws OrderException {
		if (!orderrepo.existsById(orderId)) {
			throw new OrderException("There is no such order id to update");
		}
		OrderModel o = parser.parse(orderrepo.findById(orderId).orElse(null));
		o.setDeliveryDate(arrivalDate);
		o.setDispatchDate(dispatchDate);
		parser.parse(orderrepo.save(parser.parse(o)));
		return true;
	}

}
