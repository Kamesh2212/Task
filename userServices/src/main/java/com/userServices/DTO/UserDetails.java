package com.userServices.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.validation.annotation.Validated;

import com.userServices.model.AccRegisterRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * UserDetails Entity
 */
@Validated
@Entity
@Table(name = "user_details") // Ensure this matches the actual table name in the database
public class UserDetails {

	public UserDetails(UUID uid, String firstname, String lastname, String username, String email, String password) {
		super();
		this.uid = uid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID uid; // Primary Key (UUID)

	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String lastname;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
    private LocalDateTime sign_in_timestamp;

	// Default no-args constructor (required by JPA)
	public UserDetails() {
	}

	// Constructor that takes in RegisterAccRegisterRequest
	public UserDetails(AccRegisterRequest reg) {
		this.firstname = reg.getFirstname();
		this.lastname = reg.getLastname();
		this.username = reg.getUsername();
		this.email = reg.getEmail();
		this.password = reg.getPassword();
		this.sign_in_timestamp = LocalDateTime.now();
	}

	// Getters and setters for all fields...
	public UUID getUid() {
		return uid;
	}

	public void setUid(UUID uid) {
		this.uid = uid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
