package com.vrs.entities;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
//For generating Getters , Setters , Constructors etc. 

@Data
@Entity
@Table(name="user_all")
public class User {
	
	@Id
	@GeneratedValue
	private int userId;
	
	private String password;
	private String role;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id_fk")
	private Customer customer;
}
