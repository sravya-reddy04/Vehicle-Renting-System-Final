package com.vrs.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//For no argument constructor ,getters and setters
@Data
//For all argument constructor
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {
	//Validating first name
	@Size(min = 2, message = "driver's first name should have atleast 2 characters")
	@Pattern(regexp = "^[a-zA-Z\\\\s]{2,}$", message = "Please enter valid First Name")
	private String firstName;
	// Validating last name
	@NotEmpty(message = "Last name cannot be empty")
	@Pattern(regexp = "^[a-zA-Z\\\\s]{1,}$", message = "Please enter valid Last Name")
	private String lastName;
	// Validating Indian contact numbers
	@NotEmpty(message = "Contact number cannot be empty")
	@Pattern(regexp = "^[+]?[0]?(91)?[789][\\d]{9}$", message = "Please enter valid contact number")
	private String contactNumber;
	// validating price
	@Positive(message = "price should be positive")
	private double chargesPerDay;
	@NotEmpty
	private String vehicleNumber;
	@NotEmpty
	private String type;// car//bus
	@NotEmpty
	private String category; // ac or nonac
	private String description;
	@NotEmpty
	private double chargesPerKM;
	@NotEmpty
	private double fixedCharges;
	
}
