package com.vrs.service;

import java.util.List;

import com.vrs.dto.DriverDto;
import com.vrs.entities.Driver;

public interface IDriverService {
	// Add Driver
	Driver addDriver(Driver driver);

	// Update Driver
	Driver updateDriver(Driver driver);

	// Delete Driver by ID
	Driver deleteDriverById(int driverId);

	// Get Driver By ID
	Driver getDriverById(int driverId);

	// Get All Drivers
	List<Driver> getAllDrivers();

	// Get all drivers ordered by their charges per day
	List<Driver> getAllDriversOrderByCharges();

	// Get all drivers ordered by their firstName
	List<Driver> getAllDriversOrderByName();

	// Get drivers from a specific location
	List<Driver> getAllDriversFromSpecficLocation(String location);

	// Get driver information along with vehicle information
	List<DriverDto> getAllDriverVehicleInfo();

	// Get driver and vehicle information with a specific booking id
	List<DriverDto> getDriverVehicleById(int id);

	// method for updating contact number
	Driver updateDriverContactNumber(int id, String contactNumber);

	// method for updating vehicle number
	Driver updateDriverEmail(int id, String email);

	// method for getting drivers who only drive AC vehicle
	List<Driver> getACDriver();

	// method for getting drivers who drives vehicle with capacity more than 4
	List<Driver> getCapacityDriver();
}
