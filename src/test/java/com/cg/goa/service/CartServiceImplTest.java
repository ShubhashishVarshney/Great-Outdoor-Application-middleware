package com.cg.goa.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.goa.dao.ICartRepository;
import com.cg.goa.entity.CartItemEntity;
import com.cg.goa.entity.ProductEntity;
import com.cg.goa.exception.CartException;
import com.cg.goa.model.CartItemModel;
import com.cg.goa.model.ProductModel;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {

	@Mock
	private ICartRepository cartrepo;

	@InjectMocks /* injecting cart repository marked as @Mock into Cart service implementation */
	private CartServiceImpl csImpl;
	
	
	/*
	 * Test Case 1 - to find cart list
	 */
	@Test
	@DisplayName("CartServiceImpl::findCartlist should return list of existing packages as CartModel List")
	void testFindCartlist() {
		BigDecimal cost=new BigDecimal("100.00");
		List<ProductEntity> p = new ArrayList<>();
		p.add(new ProductEntity(1L, "cookies", cost, "cookies", "red", "grocery", 2, "Rakesh Kumar", "Sweet"));
		p.add(new ProductEntity(2L, "cookies", cost, "cookies", "red", "grocery", 2, "Rakesh gupta", "Sweet"));
		List<ProductModel> pm = new ArrayList<>();
		pm.add(new ProductModel(1L, "cookies", cost, "cookies", "red", "grocery", 2, "Rakesh Kumar", "Sweet"));
		pm.add(new ProductModel(2L, "cookies", cost, "cookies", "red", "grocery", 2, "Rakesh gupta", "Sweet"));
		List<CartItemEntity> testdata = Arrays.asList(new CartItemEntity[] {
				new CartItemEntity(1L, 1,cost, 2L),
				new CartItemEntity(2L, 2, cost, 2L), });
		List<CartItemModel> expected = Arrays.asList(new CartItemModel[] {
				new CartItemModel(1L, 1,cost, 2L),
				new CartItemModel(2L, 2, cost, 2L) });
		Mockito.when(cartrepo.findAll()).thenReturn(testdata);
		List<CartItemModel> actual = csImpl.findCartlist();
		assertEquals(expected, actual);
	}

	/*
	 * Test Case 2 - to Add item to cart
	 * @throws cart exception
	 */
	@Test
	@DisplayName("CartServiceImpl:: addCart should add Cart to list of existing Package as CartModel list")
	void testAddCart() throws CartException {
		BigDecimal cost=new BigDecimal("100.00");
		List<ProductEntity> p = new ArrayList<>();
		p.add(new ProductEntity(1L, "cookies", cost, "cookies", "red", "grocery", 2, "Rakesh Kumar", "Sweet"));
		p.add(new ProductEntity(2L, "cookies", cost, "cookies", "red", "grocery", 2, "Rakesh gupta", "Sweet"));
		List<ProductModel> pm = new ArrayList<>();
		pm.add(new ProductModel(1L, "cookies", cost, "cookies", "red", "grocery", 2, "Rakesh Kumar", "Sweet"));
		pm.add(new ProductModel(2L, "cookies", cost, "cookies", "red", "grocery", 2, "Rakesh gupta", "Sweet"));
		CartItemEntity testdata = new CartItemEntity(1L, 1,cost, 2L);
		CartItemModel expected = new CartItemModel(1L, 2, cost, 2L);
	    Mockito.when(cartrepo.existsById(testdata.getCartId())).thenReturn(false);
		CartItemModel actual = csImpl.addCart(expected);
		assertEquals(expected, actual);
	}

	
	/*
	 * Test Case 3 - to update item to cart
	 * @throws cart exception
	 */
	@Test
	@DisplayName("CartServiceImpl:: updateCart should update Cart to list of existing Package as CartModel list")
	void testUpdateCart() throws CartException {
		BigDecimal cost=new BigDecimal("100.00");
		List<ProductEntity> p = new ArrayList<>();
		p.add(new ProductEntity(1L, "cookies", cost, "cookies", "red", "grocery", 2, "Rakesh Kumar", "Sweet"));
		p.add(new ProductEntity(2L, "cookies", cost, "cookies", "red", "grocery", 2, "Rakesh gupta", "Sweet"));
		List<ProductModel> pm = new ArrayList<>();
		pm.add(new ProductModel(1L, "cookies", cost, "cookies", "red", "grocery", 2, "Rakesh Kumar", "Sweet"));
		pm.add(new ProductModel(2L, "cookies", cost, "cookies", "red", "grocery", 2, "Rakesh gupta", "Sweet"));
		CartItemEntity testdata = new CartItemEntity(1L, 1,cost, 2L);
		CartItemModel expected = new CartItemModel(1L, 2, cost, 2L);
	    Mockito.when(cartrepo.existsById(testdata.getCartId())).thenReturn(true);
		CartItemModel actual = csImpl.updateCart(expected);
		assertEquals(expected, actual);
	}	
	/*
	 * Test Case 4 - to delete cart list
	 * @throws cart exception
	 */
	@Test
	@DisplayName("CartServiceImpl:: deleteCartlist should delete Cart List to list of existing Package as CartModel list")
	void TestdeleteCartlist() throws CartException {
		BigDecimal cost=new BigDecimal("100.00");
		List<ProductEntity> p = new ArrayList<>();
		p.add(new ProductEntity(1L, "cookies", cost, "cookies", "red", "grocery", 2, "Rakesh Kumar", "Sweet"));
		p.add(new ProductEntity(2L, "cookies", cost, "cookies", "red", "grocery", 2, "Rakesh gupta", "Sweet"));
		List<ProductModel> pm = new ArrayList<>();
		pm.add(new ProductModel(1L, "cookies", cost, "cookies", "red", "grocery", 2, "Rakesh Kumar", "Sweet"));
		pm.add(new ProductModel(2L, "cookies", cost, "cookies", "red", "grocery", 2, "Rakesh gupta", "Sweet"));
		CartItemEntity testdata = new CartItemEntity(1L, 1,cost, 2L);
		CartItemModel expected = new CartItemModel(1L, 1, cost, 2L);
		Mockito.doNothing().when(cartrepo).deleteByuserId(1);
	assertTrue(csImpl.deleteCartlist(expected.getUserId()));
	}
}
