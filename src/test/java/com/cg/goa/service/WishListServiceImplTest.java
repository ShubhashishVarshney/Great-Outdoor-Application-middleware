package com.cg.goa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import com.cg.goa.dao.IWishlistRepository;
import com.cg.goa.entity.WishlistItemEntity;
import com.cg.goa.exception.WishlistException;
import com.cg.goa.model.WishlistItemModel;

@ExtendWith(MockitoExtension.class)
public class WishListServiceImplTest {

	@Mock
	private IWishlistRepository wishrepo;

	@InjectMocks /*
					 * injecting package repository marked as @Mock into package service
					 * implementation
					 */
	private WishListServiceImpl wsImpl;
	/*
	 * test case 1- to Findall items in wishlist
	 */
	@Test
	@DisplayName("WishlistServiceImpl::findAll should return list of existing wishlist items as Wishlistitem Model List")
	void testFindAllItems() {
	     List<WishlistItemEntity> testdata = Arrays.asList(new WishlistItemEntity[] { 
	    		 new WishlistItemEntity(1L,"U01","11"),
	    		 new WishlistItemEntity(2L, "U01", "11"),
		   });
		List<WishlistItemModel> expected = Arrays.asList(new WishlistItemModel[] {
				new WishlistItemModel(1L,"U01","11"),	
				new WishlistItemModel(2L, "U01", "11") });
		Mockito.when(wishrepo.findAll()).thenReturn(testdata);
		List<WishlistItemModel> actual = wsImpl.findAllItems();
		assertEquals(expected, actual);

	}
	/*
	 * test case 2- to Find  wishlist
	 */
	@Test
	@DisplayName("WishlistServiceImpl::findWishlist should return list to existing WishlistService as WishlistItem Model List")
	void testFindWishlist() {
	     List<WishlistItemEntity> testdata = Arrays.asList(new WishlistItemEntity[] { 
	    		 new WishlistItemEntity(1L,"U01","11"),
	    		 new WishlistItemEntity(2L, "U01", "11"),
		   });
		List<WishlistItemModel> expected = Arrays.asList(new WishlistItemModel[] {
				new WishlistItemModel(1L,"U01","11"),	
				new WishlistItemModel(2L, "U01", "11") });
			Mockito.when(wishrepo.findByuserId(testdata.get(0).getUserId())).thenReturn(testdata);
		List<WishlistItemModel> actual = wsImpl.findWishlist(expected.get(0).getUserId());
		assertEquals(expected, actual);

	}
	/*
	 * test case 3- to Find items in wishlist
	 */
	@Test
	@DisplayName("WishlistServiceImpl::findWishlistItem should find all wishlistItem in WishlistItem Model List")
	void testFindWishlistItem() throws WishlistException {
		 List<WishlistItemEntity> testdata = Arrays.asList(new WishlistItemEntity[] { 
	    		 new WishlistItemEntity(1L,"U01","11"),
	    		 new WishlistItemEntity(2L, "U01", "11"),
		   });
		List<WishlistItemModel> expected = Arrays.asList(new WishlistItemModel[] {
				new WishlistItemModel(1L,"U01","11"),	
				new WishlistItemModel(2L, "U01", "11") });
		Mockito.when(wishrepo.findAll()).thenReturn(testdata);
		Mockito.when(wishrepo.findByuserId(testdata.get(0).getUserId())).thenReturn(testdata);
		List<WishlistItemModel> actual = wsImpl.findWishlistItem(expected.get(0).getProductId(), expected.get(0).getUserId());
		assertEquals(expected, actual);

	}
	/*
	 * test case 4- to Add product to wishlist
	 */
	@Test
	@DisplayName("WishlistServiceImpl::addProductToWishlist should add product to WishlistItem Model List")
	void testAddProductToWishlist() {
		WishlistItemEntity testdata = new WishlistItemEntity(1L, "U01", "11");
		WishlistItemModel expected = new WishlistItemModel(1L, "U01", "11");
		Mockito.when(wishrepo.existsById(testdata.getWishlistId())).thenReturn(true);
		Mockito.when(wishrepo.findById(testdata.getWishlistId())).thenReturn(Optional.of(testdata));
		boolean actual = wsImpl.addProductToWishlist(expected.getProductId(), expected.getWishlistId());
		assertTrue( actual);
	}
	/*
	 * test case 5- to Delete item in wishlist
	 * @ Wishlist Exception
	 */
	@Test
	@DisplayName("WishlistServiceImpl::deleteWishlistItem should delete WishlistItem in WishlistItem Model List")
	void testDeleteWishlistItem() throws WishlistException {
		 List<WishlistItemEntity> testdata = Arrays.asList(new WishlistItemEntity[] { 
	    		 new WishlistItemEntity(1L,"U01","11"),
	    		 new WishlistItemEntity(2L, "U01", "11"),
		   });
		List<WishlistItemModel> expected = Arrays.asList(new WishlistItemModel[] {
				new WishlistItemModel(1L,"U01","11"),	
				new WishlistItemModel(2L, "U01", "11") });
		Mockito.when(wishrepo.findAll()).thenReturn(testdata);
		boolean actual = wsImpl.deleteWishlistItem(expected.get(0).getProductId(), expected.get(0).getUserId());
		assertTrue( actual);
	}
	/*
	 * test case 6- to Delete wishlist
	 * @ Wishlist Exception
	 */
	@Test
	@DisplayName("WishlistServiceImpl::deleteWishlist should delete all WishlistItem in WishlistItem Model List")
	void testDeleteWishlist() throws WishlistException {
		WishlistItemEntity testdata = new WishlistItemEntity(1L, "U01", "11");
		WishlistItemModel expected = new WishlistItemModel(1L, "U01", "11");
		boolean actual = wsImpl.deleteWishlist(expected.getUserId());
		assertTrue(actual);
	}
	/*
	 * test case 7- to Add item in wishlist
	 * @ Wishlist Exception
	 */
	@Test
	@DisplayName("WishlistServiceImpl::addWishlistItem should add one Item in WishlistItem Model List")
	void testAddWishlistItem() throws WishlistException {
	WishlistItemEntity testdata = new WishlistItemEntity(1L, "U01", "11");
		WishlistItemModel expected = new WishlistItemModel(1L, "U01", "11");
		Mockito.when(wishrepo.existsById(testdata.getWishlistId())).thenReturn(false);
		WishlistItemModel actual = wsImpl.addWishlistItem(expected);
		assertEquals(expected, actual);
	}
}
