package com.cg.goa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.goa.dao.IOrderRepository;
import com.cg.goa.entity.OrderEntity;
import com.cg.goa.exception.OrderException;
import com.cg.goa.model.OrderModel;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
	@Mock
	private IOrderRepository orderrepo;

	@InjectMocks /*
					 * injecting package repository marked as @Mock into package service
					 * implementation
					 */
	private OrderServiceImpl osImpl;
	/*
	 * Test Case 1 - to Find Orders By UserId
	 */
	@Test
	@DisplayName("OrderServiceImpl::Find Orders By User Id should find orders by Id in OrderModel List")
	void testFindOrdersByUserId() {
		BigDecimal cost = new BigDecimal("100.00");
		List<OrderEntity> testdata = Arrays.asList(new OrderEntity[] {
				new OrderEntity(1, "U01", cost, 1L, LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),
						LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30))),
				new OrderEntity(2, "U02", cost, 1L, LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),
						LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30))) });
		List<OrderModel> expected = Arrays.asList(new OrderModel[] {
				new OrderModel(1, "U01", cost, 1L, LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),
						LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30))),
				new OrderModel(2, "U02", cost, 1L, LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),
						LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30))) });
		Mockito.when(orderrepo.findByuserId(testdata.get(0).getUserId())).thenReturn(testdata);
		List<OrderModel> actual = osImpl.findOrdersByUserId(expected.get(0).getUserId());
		assertEquals(expected, actual);

	}
	/*
	 * Test Case 2 - to Find All Orders
	 */
	@Test
	@DisplayName("OrderServiceImpl::Find All Orders should return list of all orders in OrderModel List")
	void testFindAllOrders() {
		BigDecimal cost = new BigDecimal("100.00");
		List<OrderEntity> testdata = Arrays.asList(new OrderEntity[] {
				new OrderEntity(1, "U01", cost, 1L, LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),
						LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30))),
				new OrderEntity(2, "U02", cost, 1L, LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),
						LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30))) });
		List<OrderModel> expected = Arrays.asList(new OrderModel[] {
				new OrderModel(1, "U01", cost, 1L, LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),
						LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30))),
				new OrderModel(2, "U02", cost, 1L, LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),
						LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30))) });
		Mockito.when(orderrepo.findAll()).thenReturn(testdata);
		List<OrderModel> actual = osImpl.findAllOrders();
		assertEquals(expected, actual);
	}
	/*
	 * Test Case 3 - to Add Order
	 * @throws OrderException
	 */
	@Test
	@DisplayName("OrderServiceImpl::Add Order should add list of orders OrderModel List")
	void testAddOrder() throws OrderException {
		BigDecimal cost = new BigDecimal("100.00");
		OrderEntity testdata = new OrderEntity(1, "U01", cost, 1L,
				LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),
				LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)));
		OrderModel expected = new OrderModel(1, "U01", cost, 1L,
				LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),
				LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)));
		Mockito.when(orderrepo.existsById(testdata.getOrderId())).thenReturn(false);
		Mockito.when(orderrepo.save(testdata)).thenReturn(testdata);
		OrderModel actual = osImpl.addOrder(expected);
		assertEquals(expected, actual);

	}
	/*
	 * Test Case 4 - to Delete All Orders
	 * @throws OrderException
	 */
	@Test
	@DisplayName("OrderServiceImpl::Delete should delete all existing order from OrderModel List")
	void testDeleteAllOrders() throws OrderException {
		BigDecimal cost = new BigDecimal("100.00");
		List<OrderEntity> testdata = Arrays.asList(new OrderEntity[] {
				new OrderEntity(1, "U01", cost, 1L, LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),
						LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30))),
				new OrderEntity(2, "U02", cost, 1L, LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),
						LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30))) });
		
		List<OrderModel> expected = Arrays.asList(new OrderModel[] {
				new OrderModel(1, "U01", cost, 1L, LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),
						LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30))),
				new OrderModel(2, "U02", cost, 1L, LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),
						LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30))) });
		Mockito.when(orderrepo.findAll()).thenReturn(testdata);
		Mockito.doNothing().when(orderrepo).deleteAll();
		assertTrue(osImpl.deleteAllOrders());
	}
	/*
	 * Test Case 5 - to Delete Order By Id
	 * @throws OrderException
	 */
	@Test
	@DisplayName("OrderServiceImpl::Delete Order By ID should Delete order by their ids OrderModel List")
	void testDeleteOrderById() throws OrderException {
		BigDecimal cost = new BigDecimal("100.00");
		OrderEntity testdata = new OrderEntity(1, "U01", cost, 1L,
				LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),
				LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)));
		OrderModel expected = new OrderModel(1, "U01", cost, 1L,
				LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),
				LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)));
		Mockito.when(orderrepo.existsById(testdata.getOrderId())).thenReturn(true);
		assertTrue(osImpl.deleteOrderById(expected.getOrderId()));
	}
	/*
	 * Test Case 6 - to Update Date
	 * @throws OrderException
	 */
	@Test
	@DisplayName("OrderServiceImpl::Update Date should Update the date  as in OrderModel List")
	void testUpdateDate() throws OrderException {
		BigDecimal cost = new BigDecimal("100.00");
		OrderEntity testdata = new OrderEntity(1, "U01", cost, 1L,
				LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),
				LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)));
		OrderModel expected = new OrderModel(1, "U01", cost, 1L,
				LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),
				LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)));
		Mockito.when(orderrepo.existsById(testdata.getOrderId())).thenReturn(true);
		Mockito.when(orderrepo.findById(testdata.getOrderId())).thenReturn(Optional.of(testdata));
		boolean actual = osImpl.updateDate(expected.getOrderId(), expected.getDispatchDate(),
				expected.getDeliveryDate());
		assertEquals(true, actual);
	}
}
