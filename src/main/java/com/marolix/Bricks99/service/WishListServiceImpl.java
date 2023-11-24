package com.marolix.Bricks99.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.marolix.Bricks99.entity.PropertyDetails;
import com.marolix.Bricks99.entity.User;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.repository.PropertyRepository;
import com.marolix.Bricks99.repository.UserRepository;

@Service(value = "wishlistService")
public class WishListServiceImpl implements WishListService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PropertyRepository propertyRepository;
	@Autowired
	private Environment environment;

	@Override
	public String addPropertyToWishList(Integer propertyId, Integer userId) throws Bricks99Exception {
		Optional<User> optional = userRepository.findById(userId);
		if(optional.isPresent())
			throw new Bricks99Exception(environment.getProperty("WISHLISTSERVICE.USER_NOTFOUND"));
		Optional<PropertyDetails> optional1 = propertyRepository.findById(propertyId);
		if(optional1.isPresent())
			throw new Bricks99Exception(environment.getProperty("WISHLISTSERVICE.PROPERTY_NOTFOUND"));
		return null;
	}

}
