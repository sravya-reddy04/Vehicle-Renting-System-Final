package com.vrs.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

//For generating Getters , Setters , Constructors etc. 

@Data
@Entity
public class Payment {
	@Id
	@GeneratedValue
	private int paymentId;
	private String paymentMode;
	private LocalDate paymentDate;
	@OneToOne(mappedBy ="payment", cascade=CascadeType.ALL)
	private Booking booking;
	private String paymentStatus;
	
}
