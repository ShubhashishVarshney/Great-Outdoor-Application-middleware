package com.cg.goa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.goa.dao.IWishlistRepository;
import com.cg.goa.exception.WishlistException;
import com.cg.goa.model.WishlistItemModel;

@Service
public class WishListServiceImpl implements IWishlistService {

	@Autowired
	private IWishlistRepository wishlistrepo;

	@Autowired
	private EMParserWishList parser;

	public WishListServiceImpl() {

	}
	/*
	 * Corresponding Getters and Setters for private members
	 * 
	 */
	public WishListServiceImpl(IWishlistRepository wishlistrepo) {
		super();
		this.wishlistrepo = wishlistrepo;
		this.parser = new EMParserWishList();
	}
	/*
	 * service implementation for Finding All Items
	 */
	@Transactional
	@Override
	public List<WishlistItemModel> findAllItems() {

		return wishlistrepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}
	/*
	 * service implementation for Finding Wishlist
	 */
	@Transactional
	@Override
	public List<WishlistItemModel> findWishlist(String userId) {
		//System.out.println("aaas");
		return wishlistrepo.findByuserId(userId).stream().map(parser::parse).collect(Collectors.toList());
	}
	/*
	 * service implementation for Finding Wishlist Item
	 * @Throws WishlistException
	 */
	@Transactional
	@Override
	public List<WishlistItemModel> findWishlistItem(String productId, String userId) throws WishlistException {
		List<WishlistItemModel> w3 = new ArrayList<WishlistItemModel>();
		List<WishlistItemModel> w2 = wishlistrepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
		for (WishlistItemModel w1 : w2) {
			if (!w1.getUserId().equalsIgnoreCase(userId)) {
				continue;
			}else {
			w3 = wishlistrepo.findByuserId(w1.getUserId()).stream().map(parser::parse).collect(Collectors.toList());
			}
		}

		return w3;
	}
	/*
	 * service implementation for Adding new Product to Wishlist 
	 */
	@Transactional
	@Override
	public boolean addProductToWishlist(String productId, Long wishlistID) {
		if (wishlistrepo.existsById(wishlistID)) {
			WishlistItemModel w = parser.parse(wishlistrepo.findById(wishlistID).orElse(null));
			w.setProductId(productId);
			parser.parse(wishlistrepo.save(parser.parse(w)));
			return true;
		}
		return false;
	}
	/*
	 * service implementation for Deleting Wishlist Item
	 * @Throws WishlistException
	 */
	@Transactional
	@Override
	public boolean deleteWishlistItem(String productId, String userId) throws WishlistException {
		List<WishlistItemModel> w2 = wishlistrepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
		for (WishlistItemModel w1 : w2) {
			if (w1.getUserId().equalsIgnoreCase(userId)) {
				if (w1.getProductId().equalsIgnoreCase(productId)) {
				wishlistrepo.deleteById(w1.getWishlistId());
			}
				else {
					continue;
				}
		}
		}
		return true;
	}
	/*
	 * service implementation for Deleting Wishlist Item
	 * @Throws WishlistException
	 */
	@Transactional
	@Override
	public boolean deleteWishlist(String userId) throws WishlistException {
		List<WishlistItemModel> w2 = wishlistrepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
		for (WishlistItemModel w1 : w2) {
			if (!w1.getUserId().equalsIgnoreCase(userId)) {
				continue;
			}
			wishlistrepo.deleteById(w1.getWishlistId());
		}
		return true;
	}
	/*
	 * service implementation for Adding Wishlist Item
	 * @Throws WishlistException
	 */
	@Transactional
	@Override
	public WishlistItemModel addWishlistItem(WishlistItemModel wishlistItem) throws WishlistException {
		if (wishlistrepo.existsById(wishlistItem.getWishlistId())) {
			throw new WishlistException("whislist is not added");
		}
		parser.parse(wishlistrepo.save(parser.parse(wishlistItem)));
		return wishlistItem;
	}

}