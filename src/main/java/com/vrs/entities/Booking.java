package com.vrs.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import lombok.Data;


//For generating Getters , Setters , Constructors etc. 
@Data
@Entity
public class Booking {
	@Id
	@GeneratedValue
	private int bookingId;
	private LocalDate bookingDate;
	private LocalDate bookedTillDate;
	private String bookingDescription;
	private double totalCost;
	private double distance;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="payment_id_fk")
	private Payment payment;
	@NotEmpty
	@OneToMany(mappedBy="booking",cascade=CascadeType.ALL)
	private List<Vehicle> vehicles=new ArrayList<>();
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id_fk")
	private Customer customer;

	@OneToOne(mappedBy="booking")
	private ActiveBooking activeBooking;
}
