package com.marolix.Bricks99.api;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marolix.Bricks99.entity.OTPDetails;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.service.EmailSender;

@RestController
@RequestMapping(value = "email-api")
@CrossOrigin
public class EmailAPI {
	@Autowired
	private EmailSender emailSender;

	@PostMapping(value = "/send-otp")
	public ResponseEntity<String> sendOTP(@RequestBody  @Valid @Pattern(regexp = "^(?:(?:\\+\\d{1,2}\\s?)?\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}|[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$\r\n"
			,message="Enter a valid mail or phone number") String email) throws Bricks99Exception {
		//System.out.println("controller");
		emailSender.sendOTP(email);

		return new ResponseEntity<String>("otp sent successfully", HttpStatus.OK);
		
		
		
		
		
	}
	
	@PostMapping(value="/send-text-otp")
	public ResponseEntity<String> sendTextOTP(@RequestBody String phonenumber) throws Bricks99Exception {

		emailSender.sendTextOTP(phonenumber);

		return new ResponseEntity<String>("otp sent successfully", HttpStatus.OK);
		
		
		
		
		
	}
	@PostMapping(value="/verify-otp")
	public ResponseEntity<String> verifyOTP(@RequestBody OTPDetails otpdetails ) throws Bricks99Exception {
	
		emailSender.verifyOTP(otpdetails);

		return new ResponseEntity<String>("otp verified successfully", HttpStatus.OK);
		
		
		
		
		
	}
}
