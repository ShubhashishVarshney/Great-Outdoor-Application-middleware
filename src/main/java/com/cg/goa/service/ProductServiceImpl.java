package com.cg.goa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.goa.dao.IProductRepository;
import com.cg.goa.exception.ProductException;
import com.cg.goa.model.ProductModel;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productrepo;

	@Autowired
	private EMParserProduct parser;
	/*
	 * A default Constructor with no implementation
	 */

	public ProductServiceImpl() {

	}
	/*
	 * Corresponding Getters and Setters for private members
	 * 
	 */
	public ProductServiceImpl(IProductRepository productrepo, EMParserProduct parser) {
		super();
		this.productrepo = productrepo;
		this.parser = new EMParserProduct();
	}
	/*
	 * service implementation for Finding All Items
	 */
	@Transactional
	@Override
	public List<ProductModel> findAllProducts() {
		return productrepo.findAll().stream().map(EMParserProduct::parse).collect(Collectors.toList());

	}
	/*
	 * service implementation for Finding Products By ProductId
	 */
	@Transactional
	@Override
	public ProductModel findByProductId(Long id) {
		return EMParserProduct.parse(productrepo.findById(id).orElse(null));
	}
	/*
	 * service implementation for Finding Products By Category
	 */
	@Transactional
	@Override
	public List<ProductModel> findByProductCategory(String productCategory) {
		return productrepo.findByCategory(productCategory).stream().map(EMParserProduct::parse)
				.collect(Collectors.toList());

	}
	/*
	 * service implementation for Adding Product to ProductEntity
	 * @Throws ProductException
	 */
	@Transactional
	@Override
	public ProductModel addProduct(ProductModel productEntity) throws ProductException {

		if (!productrepo.existsById(productEntity.getProductId())) {
			EMParserProduct.parse(productrepo.save(EMParserProduct.parse(productEntity)));
			return productEntity;
		} else {
			throw new ProductException("Product id already exists ");
		}
	}
	/*
	 * service implementation for Updating existing Product
	 * @Throws ProductException
	 */
	@Transactional
	@Override
	public ProductModel updateProduct(ProductModel productEntity) throws ProductException {
		if (productrepo.existsById(productEntity.getProductId())) {
			EMParserProduct.parse(productrepo.save(EMParserProduct.parse(productEntity)));
			return productEntity;
		} else {
			throw new ProductException("Product id not exists ");
		}

	}
	/*
	 * service implementation for Updating Product Quantity
	 */
	@Transactional
	@Override
	public boolean updateProductQuantity(Integer quantity, Long productId) {
		if (productId != null) {
			if (productrepo.existsById(productId)) {
				ProductModel p=	EMParserProduct.parse(productrepo.findById(productId).orElse(null));
				p.setQuantity(quantity);
				return true;
			}
		}
		return false;
	}
	/*
	 * service implementation for deleting Product By ProductId
	 * @Throws ProductException
	 */
	@Transactional
	@Override
	public boolean deleteByProductId(Long id) throws ProductException {
			if (productrepo.findById(id) != null) {
				productrepo.deleteById(id);
				return true;
			} else {
				throw new ProductException("id cannot be nullor not found");
			}
	}
	/*
	 * service implementation for Searching Product By product name
	 */
	@Transactional
	@Override
	public List<ProductModel> search(String keyword) {

		return productrepo.findByproductName(keyword).stream().map(EMParserProduct::parse).collect(Collectors.toList());
	}
	/*
	 * service implementation for Filtering Product
	 */
	@Transactional
	@Override
	public List<ProductModel> filter(BigDecimal maxPrice) {

		return productrepo.findByprice(maxPrice).stream().map(EMParserProduct::parse).collect(Collectors.toList());
	}

}
