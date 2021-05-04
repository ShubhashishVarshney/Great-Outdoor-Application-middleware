package com.cg.goa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.goa.dao.ICustomerRepository;
import com.cg.goa.entity.CustomerEntity;
import com.cg.goa.model.CustomerModel;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepo;

	@Autowired
	private EMParserCustomer parser;
	/*
	 * A default Constructor with no implementation
	 */

	public CustomerServiceImpl() {

	}
	/*
	 * Corresponding Getters and Setters for private members
	 * 
	 */
	public CustomerServiceImpl(ICustomerRepository customerRepo) {
		super();
		this.customerRepo = customerRepo;
		this.parser = new EMParserCustomer();
	}
	/*
	 * service implementation for Getting  All Customers
	 */
	@Transactional
	@Override
	public List<CustomerModel> getAllCustomers() {

		return customerRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());

	}
	/*
	 * service implementation for Adding Customer
	 */
	@Transactional
	@Override
	public CustomerModel addCustomer(CustomerModel customer) {
		if (!customerRepo.existsById(customer.getCustomerId())) {
			parser.parse(customerRepo.save(parser.parse(customer)));
		
			}
			return	customer;

	}

	/*
	 * service implementation for Updating  deatils of existing Customer
	 */
	@Transactional
	@Override
	public CustomerModel updateCustomer(CustomerModel customer) {
		if (customerRepo.existsById(customer.getCustomerId())) {
			parser.parse(customerRepo.save(parser.parse(customer)));
	
		}
		return	customer;
	}
	/*
	 * service implementation for Removing Customer
	 */
	@Transactional
	@Override
	public boolean removeCustomer(CustomerModel customer) {
		if (customerRepo.findById(customer.getCustomerId())!=null) {
			customerRepo.deleteById(customer.getCustomerId());
			return true;
		}
		return false;
	}
	/*
	 * service implementation for View  Customer
	 */
	@Transactional
	@Override
	public CustomerModel viewCustomer(CustomerModel customer) {
		Optional<CustomerEntity> oo=customerRepo.findById(customer.getCustomerId());
		CustomerModel cc =parser.parse( oo.get());
		return cc;
	}
}