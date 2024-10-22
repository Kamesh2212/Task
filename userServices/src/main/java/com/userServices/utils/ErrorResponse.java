package com.userServices.utils;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class ErrorResponse {
	private HttpStatus status;
	private LocalDateTime timestamp = LocalDateTime.now();;
	private Object details;

	public ErrorResponse() {
		this.timestamp = LocalDateTime.now(); // Set the current timestamp
	}

	public ErrorResponse(Object details) {
		super();
		this.details = details;
	}

	public ErrorResponse(HttpStatus status, Object details) {
		super();
		this.status = status;
		this.details = details;
	}

	public ErrorResponse(LocalDateTime timestamp) {
		super();
		this.timestamp = LocalDateTime.now();
	}

	public ErrorResponse(LocalDateTime timestamp, Object details) {
		super();
		this.timestamp = LocalDateTime.now();
		this.details = details;
	}

	public ErrorResponse(HttpStatus status, LocalDateTime timestamp) {
		super();
		this.status = status;
		this.timestamp = LocalDateTime.now();

	}

	// Getters and Setters
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Object getDetails() {
		return details;
	}

	public void setDetails(Object details) {
		this.details = details;
	}

}
