package com.cg.goa.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.goa.exception.WishlistException;
import com.cg.goa.model.WishlistItemModel;
import com.cg.goa.service.IWishlistService;

@RestController
@RequestMapping(path = "/WishlistItemEntity")
public class WishListApi {

	@Autowired
	private IWishlistService wishlistservice;
	/*
	 * to retrieve All Items in Wishlist
	 * return : List
	 * params : NIL
	 */
	@GetMapping
	public ResponseEntity<List<WishlistItemModel>> findAllItems() {
		return ResponseEntity.ok(wishlistservice.findAllItems());
	}
	/*
	 * to retrieve Items in Wishlist by userId
	 * return : List
	 * params : userId
	 */
	@GetMapping("/{userid}")
	public ResponseEntity<List<WishlistItemModel>> findWishlist(@PathVariable("userid")String userId) {

		return ResponseEntity.ok(wishlistservice.findWishlist(userId));
	}
	/*
	 * to retrieve Items in Wishlist by productId
	 * return : List
	 * params : productId
	 */
	@GetMapping("findWishlistItem/{productId},{id}")
	public ResponseEntity<List<WishlistItemModel>> findWishlistItem(@PathVariable("productId") String productId,
			@PathVariable("id") String userId) throws WishlistException {

		return ResponseEntity.ok(wishlistservice.findWishlistItem(productId, userId));
	}
	/*
	 * to Add product in Wishlist By productId, WishlistId
	 * return : List
	 * params : productId, WishlistId
	 */
	@PutMapping("/{productId},{id}")
     public ResponseEntity<Boolean> addProduct(@PathVariable("productId") String productId,
			@PathVariable("id") Long WishListId) throws WishlistException {
		Boolean w = wishlistservice.addProductToWishlist(productId, WishListId);
		return new ResponseEntity<>(w, HttpStatus.CREATED);
	}
	/*
	 * to Delete Items in Wishlist By productId, userId
	 * return : Boolean
	 * params : productId,userId
	 */
	@DeleteMapping("deleteWishlistItem/{productId},{userId}")
	public ResponseEntity<Boolean> deleteWishlistItem(@PathVariable(value = "productId") String productId,	@PathVariable("userId") String userId) throws WishlistException {		
				return ResponseEntity.ok(wishlistservice.deleteWishlistItem(productId, userId));
	}
	/*
	 * to Delete Items in Wishlist By userId
	 * return : Boolean
	 * params : userId
	 */
	@DeleteMapping("deleteWishlist/{userId}")
	public ResponseEntity<Boolean> deleteWishlist(@PathVariable(value = "userId") String userId)throws WishlistException {
		return ResponseEntity.ok(wishlistservice.deleteWishlist(userId));
	}
	/*
	 * to Add Item in Wishlist
	 * return : WishlistItemModel
	 * params : NIL
	 */
	@PostMapping
	public ResponseEntity<WishlistItemModel> addWishlistItem(WishlistItemModel wishlistItem) throws WishlistException {
		WishlistItemModel p = wishlistservice.addWishlistItem(wishlistItem);
		return new ResponseEntity<>(p, HttpStatus.CREATED);

	}

}