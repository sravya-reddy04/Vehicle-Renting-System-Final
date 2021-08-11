package com.vrs.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vrs.dto.DriverDto;
import com.vrs.entities.Driver;
import com.vrs.service.IDriverService;

@RestController
public class DriverController {

	@Autowired
	IDriverService driverService;

	/*
	 * Method for returning driver object with driver id same as that passed in url
	 */

	@GetMapping("/driver/{id}")
	public ResponseEntity<Driver> getDriverById(@PathVariable("id") int id) {
		return new ResponseEntity<>(driverService.getDriverById(id), HttpStatus.OK);
	}

	/*
	 * Method for adding new driver object in the database
	 */
	@PostMapping("/driver/")
	public ResponseEntity<Driver> addDriver(@Valid @RequestBody Driver driver) {
		return new ResponseEntity<>(driverService.addDriver(driver), HttpStatus.OK);
	}

	/*
	 * Method for updating already existing driver object present in the database
	 */
	@PutMapping("/driver")
	public ResponseEntity<Driver> updateDriver(@Valid @RequestBody Driver driver) {
		return new ResponseEntity<>(driverService.updateDriver(driver), HttpStatus.OK);
	}

	/*
	 * Method for deleting driver object with driver id same as that passed in the
	 * url
	 */
	@DeleteMapping("/driver/{id}")
	public ResponseEntity<Driver> deleteDriverById(@PathVariable("id") int id) {
		return new ResponseEntity<>(driverService.deleteDriverById(id), HttpStatus.OK);
	}

	/*
	 * Method for getting all the drivers ordered according to the charges they
	 * charge per day
	 */

	@GetMapping("/drivers/orderedByCharges")
	public ResponseEntity<List<Driver>> getDriversOrderedbyCharges() {
		return new ResponseEntity<>(driverService.getAllDriversOrderByCharges(), HttpStatus.OK);
	}

	/*
	 * Method for returning all the driver objects present in the database
	 */

	@GetMapping("/driver/all")
	public ResponseEntity<List<Driver>> getAllDriver() {
		return new ResponseEntity<>(driverService.getAllDrivers(), HttpStatus.OK);
	}

	/*
	 * Method for getting all the drivers ordered according to their First Name
	 */
	@GetMapping("/drivers/orderedByName")
	public ResponseEntity<List<Driver>> getDriversOrderedbyName() {
		return new ResponseEntity<>(driverService.getAllDriversOrderByName(), HttpStatus.OK);
	}

	/*
	 * Method for getting all the drivers from a specific location
	 */
	@GetMapping("/drivers/{location}")
	public ResponseEntity<List<Driver>> getDriversFromSpecificLocation(@PathVariable("location") String location) {
		return new ResponseEntity<>(driverService.getAllDriversFromSpecficLocation(location), HttpStatus.OK);
	}

	/*
	 * Method for getting driver and vehicle combined information
	 */
	@GetMapping("/driver/vehicle")
	public ResponseEntity<List<DriverDto>> getAllDriverVehicleInfo() {
		return new ResponseEntity<>(driverService.getAllDriverVehicleInfo(), HttpStatus.OK);
	}

	/*
	 * Method for getting driver and vehicle information with a specific booking id
	 */
	@GetMapping("/driver/vehicle/{id}")
	public ResponseEntity<List<DriverDto>> getDriverVehicleById(@PathVariable("id") int id) {
		return new ResponseEntity<>(driverService.getDriverVehicleById(id), HttpStatus.OK);
	}

	/*
	 * Method for updating driver's contact Number
	 */
	@PatchMapping("/driver/{id}/contactNumber")
	public ResponseEntity<Driver> updateDriverContactNumber(@PathVariable("id") int id,
			@RequestBody String contactNumber) {
		return new ResponseEntity<>(driverService.updateDriverContactNumber(id, contactNumber), HttpStatus.OK);
	}

	/*
	 * Method for updating driver's email Address
	 */
	@PatchMapping("/driver/{id}/email")
	public ResponseEntity<Driver> updateDriverEmail(@PathVariable("id") int id, @RequestBody String email) {
		return new ResponseEntity<>(driverService.updateDriverEmail(id, email), HttpStatus.OK);
	}

	/*
	 * Method for getting all drivers who drives AC vehicle
	 */
	@GetMapping("/driver/AC")
	public ResponseEntity<List<Driver>> getACDriver() {
		return new ResponseEntity<>(driverService.getACDriver(), HttpStatus.OK);
	}

	/*
	 * method for getting all drivers who drives vehicle whose capacity of carrying
	 * people is more than 4
	 */
	@GetMapping("/driver/capacity")
	public ResponseEntity<List<Driver>> getCapacityDriver() {
		return new ResponseEntity<>(driverService.getCapacityDriver(), HttpStatus.OK);
	}
}
