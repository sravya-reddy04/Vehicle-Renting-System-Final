package com.vrs.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//For generating Getters , Setters , Constructors etc. 

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
	
	@Id
	@GeneratedValue
	private int customerId;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String emailId;
	private String address;
	@NotEmpty
	@OneToMany(mappedBy="customer",cascade = CascadeType.ALL)
	private List<Booking> bookings = new ArrayList<>();
	
	@OneToOne(mappedBy="customer",cascade=CascadeType.ALL)
	private User user;
}
