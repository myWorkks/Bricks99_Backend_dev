package com.marolix.Bricks99.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OTPDetails {
	@Id
	private String attribute;
	private String otp;
	private LocalDateTime time;

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "OTPDetails [attribute=" + attribute + ", otp=" + otp + ", time=" + time + "]";
	}

}
