package com.marolix.Bricks99.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.marolix.Bricks99.entity.OTPDetails;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.repository.OTPRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service(value = "emailSenderService")
public class EmailSenderImpl implements EmailSender {
	@Value("${twilio.accountSid}")
	private String accountSid;

	@Value("${twilio.authToken}")
	private String authToken;

	@Value("${twilio.phoneNumber}")
	private String twilioPhoneNumber;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private OTPRepository otpRepository;
	@Autowired
	private Environment environment;

	@Override
	public void sendOTP(String email) throws Bricks99Exception {
		// System.out.println("in service");
		Context context = new Context();
		final String otp = generateOTP();
		context.setVariable("otp", otp);
		String htmlContent = templateEngine.process("OTPemail", context);
		String subject = "OTP for verification";
		saveOTP(email, otp);
		sendEmail(true, email, subject, htmlContent);

	}

	private String generateOTP() {

		String s = "";
		for (int i = 0; i < 6; i++)
			s += (int) (Math.random() * 10);
		return s;

	}

	private void sendEmail(boolean html, String... args) throws Bricks99Exception {
		System.out.println("send email called");
		MimeMessage mm = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mm, html);
			// helper.setFrom("bharath@marolix.com");

			helper.setTo(args[0]);
			helper.setText(args[2], html);
			helper.setSubject(args[1]);

			javaMailSender.send(mm);
		} catch (Exception e) {

			throw new Bricks99Exception(e.getMessage());
		}
	}

	@Override
	public void sendRegistrationMail(String... args) throws Bricks99Exception {
		Context context = new Context();
		context.setVariable("userName", args[0]);
		String htmlContent = templateEngine.process("registration", context);
		String subject = "Registration Successful";
		sendEmail(true, args[1], subject, htmlContent);

	}

	@Override
	public void sendTextOTP(String phonenumber) throws Bricks99Exception {

		Twilio.init(accountSid, authToken);
		// System.out.println("after init");
		final String otp = generateOTP();
		String msg = "Your OTP for Bricks99 is " + otp;
		saveOTP(phonenumber, otp);
		Message message = Message.creator(new PhoneNumber(phonenumber), new PhoneNumber(twilioPhoneNumber), msg)
				.create();
	}

	@Override
	public void verifyOTP(OTPDetails otpDetails) throws Bricks99Exception {
		System.out.println(otpDetails.getAttribute());
		Optional<OTPDetails> otp = otpRepository.findById(otpDetails.getAttribute());
		// System.out.println(otp.get()+" get method");
		OTPDetails details = otp
				.orElseThrow(() -> new Bricks99Exception(environment.getProperty("EMAILSERVICE.NOT_FOUND")));
		System.out.println("entity : " + details);
		if (otpDetails.getOtp().equals(details.getOtp())) {

			otpRepository.delete(details);
		} else
			throw new Bricks99Exception(environment.getProperty("EMAILSERVICE.OTP_MISMATCH"));
	}

	private void saveOTP(String attribute, String otp) throws Bricks99Exception {
		Optional<OTPDetails> details = otpRepository.findById(attribute);
		if (details.isPresent())
			throw new Bricks99Exception(environment.getProperty("EMAIL_SERVICE.OTP_SENT"));
		OTPDetails o = new OTPDetails();
		o.setAttribute(attribute);
		o.setOtp(otp);
		o.setTime(LocalDateTime.now());
		otpRepository.save(o);

	}
}
