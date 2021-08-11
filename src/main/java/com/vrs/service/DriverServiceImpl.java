package com.vrs.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vrs.VehicleRentingSystemApplication;
import com.vrs.dao.IDriverRepository;
import com.vrs.dto.DriverDto;
import com.vrs.entities.Driver;
import com.vrs.exception.DriverFoundException;
import com.vrs.exception.DriverNotFoundException;

@Service
public class DriverServiceImpl implements IDriverService {
	final static Logger LOGGER = LogManager.getLogger(VehicleRentingSystemApplication.class);

	// Injecting dependency
	@Autowired
	IDriverRepository driverRepository;

	// Saving driver in database
	@Override
	public Driver addDriver(Driver driver) {
		// Checking if driver already exists
		Optional<Driver> optional = driverRepository.findById(driver.getDriverId());
		if (optional.isPresent()) {
			throw new DriverFoundException("A Driver already exist with same driver id please provide unique driver id");
		}
		return driverRepository.save(driver);
	}

	// Updating already existing driver
	@Override
	public Driver updateDriver(Driver driver) {
		Optional<Driver> optional = driverRepository.findById(driver.getDriverId());
		if (!(optional.isPresent())) {
			throw new DriverNotFoundException("There is no existing driver to be updated with provided driver id");

		}
		Driver dbDriver = optional.get();
		dbDriver.setFirstName(driver.getFirstName());
		dbDriver.setLastName(driver.getLastName());
		dbDriver.setContactNumber(driver.getContactNumber());
		dbDriver.setAddress(driver.getAddress());
		dbDriver.setEmail(driver.getEmail());
		dbDriver.setLicenseNo(driver.getLicenseNo());
		dbDriver.setChargesPerDay(driver.getChargesPerDay());
		driverRepository.save(dbDriver);
		return dbDriver;
	}

	// Deleting driver's information from database with a specific id
	@Override
	public Driver deleteDriverById(int driverId) {

		Optional<Driver> optional = driverRepository.findById(driverId);

		if (optional.isPresent()) {
			driverRepository.deleteById(driverId);
		} else {
			throw new DriverNotFoundException("There is no existing driver to be deleted with provided driver id");
		}
		return optional.get();
	}

	// Retrieving a driver's information with a specific id
	@Override
	public Driver getDriverById(int driverId) {
		Optional<Driver> optional = driverRepository.findById(driverId);
		if (!(optional.isPresent())) {
			throw new DriverNotFoundException("No driver found with the provided driver id");
		}
		return driverRepository.findById(driverId).get();
	}

	// Retrieving all the drivers from database
	@Override
	public List<Driver> getAllDrivers() {
		Optional<List<Driver>> optional = Optional.of((List<Driver>) driverRepository.findAll());
		if (optional.get().size() != 0) {
			return optional.get();
		} else {
			throw new DriverNotFoundException("No drivers in the database");
		}
	}

	// Retrieving all the drivers from database ordered by charges they take per day
	@Override
	public List<Driver> getAllDriversOrderByCharges() {
		Optional<List<Driver>> optional = Optional.of(driverRepository.getAllDriversOrderByCharges());
		if (optional.get().size() != 0) {
			return optional.get();
		} else {
			throw new DriverNotFoundException("No drivers in the database");
		}
	}

	// Retrieving all the drivers from database ordered by their first name
	@Override
	public List<Driver> getAllDriversOrderByName() {
		Optional<List<Driver>> optional = Optional.of(driverRepository.getAllDriversOrderByName());
		if (optional.get().size() != 0) {
			return optional.get();
		} else {
			throw new DriverNotFoundException("No drivers in the database");
		}
	}

	// Retrieving all the drivers from database with a specific location
	@Override
	public List<Driver> getAllDriversFromSpecficLocation(String location) {
		Optional<List<Driver>> optional = Optional.of(driverRepository.getAllDriversFromSpecficLocation(location));
		if (optional.get().size() != 0) {
			return optional.get();
		} else {
			throw new DriverNotFoundException("No drivers in the database having similar location");
		}
	}

	// Retrieving selected fields from driver and vehicle tables
	@Override
	public List<DriverDto> getAllDriverVehicleInfo() {
		Optional<List<DriverDto>> optional = Optional.of(driverRepository.getAllDriverVehicleInfo());
		if (optional.get().size() != 0) {
			return optional.get();
		} else {
			throw new DriverNotFoundException("No driver and vehicle mapped data in the database");
		}
	}

	// Retrieving selected fields from driver and vehicle tables with a specific
	// booking id
	@Override
	public List<DriverDto> getDriverVehicleById(int id) {
		Optional<List<DriverDto>> optional = Optional.of(driverRepository.getDriverVehicleInfoById(id));
		if (optional.get().size() == 0) {
			throw new DriverNotFoundException(
					"No driver and vehicle mapped data in the database with the given booking id");

		} else {
			return optional.get();
		}
	}

	// Updating driver's contact number
	@Override
	public Driver updateDriverContactNumber(int id, String contactNumber) {
		// Find driver
		Optional<Driver> opt = driverRepository.findById(id);
		// Update driver detail
		if (opt.isPresent()) {
			Driver dbDriver = opt.get();

			dbDriver.setContactNumber(contactNumber);
			driverRepository.save(dbDriver);
			return dbDriver;

		} else {
			throw new DriverNotFoundException("There is no existing driver to be updated with provided driver id");
		}

	}

	// Updating driver's email address
	@Override
	public Driver updateDriverEmail(int id, String email) {
		// Find driver
		Optional<Driver> opt = driverRepository.findById(id);
		// Update driver detail
		if (opt.isPresent()) {
			Driver dbDriver = opt.get();

			dbDriver.setEmail(email);
			driverRepository.save(dbDriver);
			return dbDriver;

		} else {
			throw new DriverNotFoundException("There is no existing driver to be updated with provided driver id");
		}

	}

	// Getting AC vehicle's drivers
	@Override
	public List<Driver> getACDriver() {
		Optional<List<Driver>> optional = Optional.of(driverRepository.getACDrivers());
		if (optional.get().size() != 0) {
			return optional.get();
		} else {
			throw new DriverNotFoundException("No drivers in the database who are driving AC Vehicles");
		}
	}

	// Getting drivers who drive vehicle with capacity more than 4
	@Override
	public List<Driver> getCapacityDriver() {
		Optional<List<Driver>> optional = Optional.of(driverRepository.getCapacityDrivers());
		if (optional.get().size() != 0) {
			return optional.get();
		} else {
			throw new DriverNotFoundException(
					"No drivers in the database who drives vehicle with capacity more than 4");
		}
	}
}