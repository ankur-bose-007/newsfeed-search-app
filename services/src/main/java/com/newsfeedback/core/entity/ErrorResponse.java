package com.newsfeedback.core.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 729706
 * Name: Ankur Bose
 * Date: Feb 28, 2019
 */
public class ErrorResponse {
	private static final long serialVersionUID = 5776681206288518465L;

	private String timestamp;
	private String errorMessage;
	private int reasonCode;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(int reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
