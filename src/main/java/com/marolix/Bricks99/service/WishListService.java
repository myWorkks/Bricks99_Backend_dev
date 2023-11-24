package com.marolix.Bricks99.service;

import com.marolix.Bricks99.exception.Bricks99Exception;

public interface WishListService {
	public String addPropertyToWishList(Integer propertyId, Integer userId) throws Bricks99Exception;
}
