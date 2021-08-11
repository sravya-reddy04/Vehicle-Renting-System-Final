package com.vrs.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class ActiveBooking {
	@Id
	@GeneratedValue
	private int activeBookingId;
	
	
	private String status;
	
	@OneToOne()
	@JoinColumn(name="booking_id_fk")
	private Booking booking;
}
