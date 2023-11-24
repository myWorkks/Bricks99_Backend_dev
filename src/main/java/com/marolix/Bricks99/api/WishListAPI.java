package com.marolix.Bricks99.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "wishlist-api")
@Validated
@CrossOrigin
public class WishListAPI {

	public ResponseEntity<String> addToWishList(@RequestParam("propId") Integer propertyId,
			@RequestParam("userId") Integer userId) {
		return null;
	}
}
