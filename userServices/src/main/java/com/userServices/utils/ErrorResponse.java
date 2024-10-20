package com.userServices.utils;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private HttpStatus status;  
    private LocalDateTime timestamp;
    private String message;
    private Object details; 
    
    
    public ErrorResponse() {
        this.timestamp = LocalDateTime.now(); // Set the current timestamp
    }
    
    public ErrorResponse(String message, Object details) {
		super();
		this.message = message;
		this.details = details;
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

	public ErrorResponse(LocalDateTime timestamp, String message, Object details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public ErrorResponse(HttpStatus status, String message, Object details) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public ErrorResponse(HttpStatus status, LocalDateTime timestamp, String message) {
		super();
		this.status = status;
		this.timestamp = timestamp;
		this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

}
