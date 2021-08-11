package com.vrs.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
//For generating Getters , Setters , Constructors etc. 

@Data
@Entity
public class Vehicle {
	@Id
	@GeneratedValue
	private  int vehicleId;
	@NotEmpty
	private String vehicleNumber;
	@NotEmpty
	private String type;//car//bus
	@NotEmpty
	private String category ; //ac or nonac
	@NotEmpty
	private String description;
	@NotEmpty
	private String location;
	@NotEmpty
	private String capacity;
	@NotEmpty
	private double chargesPerKM;
	@NotEmpty
	private double fixedCharges;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="booking_id_fk")
	private Booking booking;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="driver_id_fk")
	private Driver driver;

	
}
	