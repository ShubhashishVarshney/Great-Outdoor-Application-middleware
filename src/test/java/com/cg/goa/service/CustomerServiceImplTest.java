package com.cg.goa.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.goa.dao.ICustomerRepository;
import com.cg.goa.entity.CustomerEntity;
import com.cg.goa.model.AddressModel;
import com.cg.goa.model.CustomerModel;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

	@Mock
	private ICustomerRepository customerrepo;

	@InjectMocks /*
					 * injecting package repository marked as @Mock into package service
					 * implementation
					 */
	private CustomerServiceImpl csImpl;
	/*
	 * test case 1- to Add customers
	 */
	@Test
	@DisplayName("CustomerServiceImpl::AddCustomer should add new customer in list as customer Model List")
	void testAddCustomer() {
		CustomerEntity testdata = new CustomerEntity(01, "shubh", "9876543212", "shubh@gmail.com",
				new AddressModel("A01", "Ablock", "greatvalue", "sector", "greaternoida", "Uttarpradesh", 203013),
				"sales");
		CustomerModel expected = new CustomerModel(01, "shubh", "9876543212", "shubh@gmail.com",
				new AddressModel("A01", "Ablock", "greatvalue", "sector", "greaternoida", "Uttarpradesh", 203013),
				"sales");
		Mockito.when(customerrepo.existsById(testdata.getCustomerId())).thenReturn(false);
		CustomerModel actual = csImpl.addCustomer(expected);
		assertEquals(expected, actual);
	}
	/*
	 * test case 2- to Update customers
	 */
	@Test
	@DisplayName("CustomerServiceImpl::UpdateCustomer should update existing deatils of customer as Customer Model")
	void testUpdateCustomer() {
		CustomerEntity testdata = new CustomerEntity(01, "shubh", "9876543212", "shubh@gmail.com",
				new AddressModel("A01", "Ablock", "greatvalue", "sector", "greaternoida", "Uttarpradesh", 203013),
				"sales");
		CustomerModel expected = new CustomerModel(01, "shubh", "9876543212", "shubh@gmail.com",
				new AddressModel("A01", "Ablock", "greatvalue", "sector", "greaternoida", "Uttarpradesh", 203013),
				"sales");
		Mockito.when(customerrepo.existsById(testdata.getCustomerId())).thenReturn(true);
		CustomerModel actual = csImpl.updateCustomer(expected);
		assertEquals(expected, actual);

	}
	/*
	 * Test Case 3 - to Remove Customer
	 */
	@Test
	@DisplayName("CustomerServiceImpl::removecustomer should remove existing customer from Customer Model")
	void testRemoveCustomer() {
		CustomerEntity testdata = new CustomerEntity(01, "shubh", "9876543212", "shubh@gmail.com",
				new AddressModel("A01", "Ablock", "greatvalue", "sector", "greaternoida", "Uttarpradesh", 203013),
				"sales");
		CustomerModel expected = new CustomerModel(01, "shubh", "9876543212", "shubh@gmail.com",
				new AddressModel("A01", "Ablock", "greatvalue", "sector", "greaternoida", "Uttarpradesh", 203013),
				"sales");
		Mockito.when(customerrepo.findById(testdata.getCustomerId())).thenReturn(Optional.of(testdata));
		boolean actual = csImpl.removeCustomer(expected);
		assertTrue(actual);
	}

}
