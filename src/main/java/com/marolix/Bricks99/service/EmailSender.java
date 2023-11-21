package com.marolix.Bricks99.service;

import com.marolix.Bricks99.entity.OTPDetails;
import com.marolix.Bricks99.exception.Bricks99Exception;

public interface EmailSender {

	public void sendOTP(String email) throws Bricks99Exception;

	public void sendRegistrationMail(String... args) throws Bricks99Exception;

	public void sendTextOTP(String phonenumber) throws Bricks99Exception;

	public void verifyOTP(OTPDetails otpDetails) throws Bricks99Exception;

}
