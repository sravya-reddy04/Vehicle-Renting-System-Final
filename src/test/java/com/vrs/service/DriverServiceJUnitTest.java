package com.vrs.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import com.vrs.VehicleRentingSystemApplication;
import com.vrs.dto.DriverDto;
import com.vrs.entities.Driver;
import com.vrs.exception.DriverFoundException;
import com.vrs.exception.DriverNotFoundException;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class DriverServiceJUnitTest {
	final static Logger LOGGER = LogManager.getLogger(VehicleRentingSystemApplication.class);
	@Autowired
	IDriverService driverService;

	// test method for getDriverById()
	@Order(3)
	@Test
	void testGetById() {
		Driver response = driverService.getDriverById(32);
		assertEquals("Deep", response.getFirstName());
		assertEquals("Kumar", response.getLastName());
		assertEquals("CG07 20171231234", response.getLicenseNo());
		assertEquals("kumar.deep@capg.com", response.getEmail());
		assertEquals("7587160882", response.getContactNumber());
		assertEquals("Bilaspur Chhattisgarh", response.getAddress());
		assertEquals(32, response.getDriverId());
		assertEquals(1250, response.getChargesPerDay());
	}

	// test method for getAllDrivers()
	@Test
	void testGetAllDrivers() {
		List<Driver> drivers = driverService.getAllDrivers();
		assertEquals(drivers.size(), 6);
	}

	// test method for addDriver()
	@Order(2)
	@Test
	void testAddDriver() {
		Driver driver = new Driver(29, "Deep", "Kumar", "7455612237", "kumar.deep@capgemini.com",
				"Bilaspur Chhattisgarh", 1300, "CG07 20171231234");
		Driver response = driverService.addDriver(driver);
		assertEquals("Deep", response.getFirstName());
		assertEquals("Kumar", response.getLastName());
		assertEquals("CG07 20171231234", response.getLicenseNo());
		assertEquals("kumar.deep@capgemini.com", response.getEmail());
		assertEquals("7455612237", response.getContactNumber());
		assertEquals("Bilaspur Chhattisgarh", response.getAddress());
		assertEquals(44, response.getDriverId());
		assertEquals(1300, response.getChargesPerDay());
	}
	
	// test method for addDriver()
		@Test
		void testAddDriverException() {
			Driver driver = new Driver(32, "Deep", "Kumar", "7455612237", "kumar.deep@capgemini.com",
					"Bilaspur Chhattisgarh", 1300, "CG07 20171231234");
			
			DriverFoundException ex = assertThrows(DriverFoundException.class,()->driverService.addDriver(driver));
			assertEquals(ex.getMessage(),"A Driver already exist with same driver id please provide unique driver id");
			
			
		}

	// test method for updateDriver()
	@Order(4)
	@Test
	void testUpdateDriver() {
		Driver driver = new Driver(32, "Deep", "Kumar", "7455612237", "kumar.deep@capgemini.com",
				"Bilaspur Chhattisgarh", 1250, "CG07 20171231234");
		Driver response = driverService.updateDriver(driver);
		assertEquals("Deep", response.getFirstName());
		assertEquals("Kumar", response.getLastName());
		assertEquals("CG07 20171231234", response.getLicenseNo());
		assertEquals("kumar.deep@capgemini.com", response.getEmail());
		assertEquals("7455612237", response.getContactNumber());
		assertEquals("Bilaspur Chhattisgarh", response.getAddress());
		assertEquals(32, response.getDriverId());
		assertEquals(1250, response.getChargesPerDay());
	}
	
	// test method for updateDriver()
		@Test
		void testUpdateDriverException() {
			Driver driver = new Driver(100, "Deep", "Kumar", "7455612237", "kumar.deep@capgemini.com",
					"Bilaspur Chhattisgarh", 1250, "CG07 20171231234");
			
			DriverNotFoundException ex = assertThrows(DriverNotFoundException.class,()->driverService.updateDriver(driver));
			assertEquals(ex.getMessage(),"There is no existing driver to be updated with provided driver id");
		}

	 //test method for deleteDriver()
	@Test
	@Order(1)
	void testDeleteDriver() {
		Driver response = driverService.deleteDriverById(43);
		assertEquals("Deep", response.getFirstName());
		assertEquals("Kumar", response.getLastName());
		assertEquals("CG07 20171231234", response.getLicenseNo());
		assertEquals("kumar.deep@capgemini.com", response.getEmail());
		assertEquals("7455612237", response.getContactNumber());
		assertEquals("Bilaspur Chhattisgarh", response.getAddress());
		assertEquals(43, response.getDriverId());
		assertEquals(1300, response.getChargesPerDay());

	}

	// test method for GetDriverOrderByName()
	@Test
	void testGetDriverOrderByName() {
		List<Driver> driver1 = driverService.getAllDriversOrderByName();
		List<Driver> driver2 = new ArrayList<Driver>(driver1);
		Collections.sort(driver1, (Driver d1, Driver d2) -> d1.getFirstName().compareTo(d2.getFirstName()));
		int size = driver1.size();
		for (int i = 0; i < size; i++) {
			assertEquals(driver1.get(i).getFirstName(), driver2.get(i).getFirstName());
//			LOGGER.info("d1:"+driver1.get(i).getFirstName()+" d2:"+driver2.get(i).getFirstName());
		}
	}

	// test method for GetDriverOrderBycharge()
	@Test
	void testGetDriverOrderByCharge() {
		List<Driver> driver1 = driverService.getAllDriversOrderByCharges();
		List<Driver> driver2 = new ArrayList<Driver>(driver1);
		Collections.sort(driver1, (Driver d1, Driver d2) -> (int) (d1.getChargesPerDay() - d2.getChargesPerDay()));
		int size = driver1.size();
		for (int i = 0; i < size; i++) {
			assertEquals(driver1.get(i).getChargesPerDay(), driver2.get(i).getChargesPerDay());
		}
	}

	// test method for getDriverFromSpecificLocation()
	@Test
	void testGetAllDriverFromSpecificLocation() {
		List<Driver> drivers = driverService.getAllDriversFromSpecficLocation("bhilai");
		for (Driver d : drivers) {
			assertTrue(d.getAddress().contains("bhilai"));
		}
	}

	// test method for updateDriverEmail()
	@Test
	void testUpdateDriverContactNumber() {
		Driver driver = driverService.updateDriverContactNumber(32, "7587160882");
		assertEquals(driver.getContactNumber(), "7587160882");

	}

	// test method for updateDriverContactNumber()
	@Test
	void testUpdateDriverEmail() {
		Driver driver = driverService.updateDriverEmail(32, "kumar.deep@capg.com");
		assertEquals(driver.getEmail(), "kumar.deep@capg.com");

	}

	// test method for getACVehicleDrivers()
	@Test
	void testGetACVehicleDriver() {
		List<Driver> drivers = driverService.getACDriver();
		for (Driver d : drivers) {
			assertEquals(d.getVehicle().getCategory(), "AC");
		}
	}

	// test Method for getDriverCapacity()
	@Test
	void testGetCapacityDriver() {
		List<Driver> drivers = driverService.getCapacityDriver();
		for (Driver d : drivers) {
			assertTrue(Integer.parseInt(d.getVehicle().getCapacity()) >= 4);
		}
	}

	// test method for getAllDriverDto()
	@Test
	void testGetAllDriverDto() {
		List<DriverDto> driverDtoList = driverService.getAllDriverVehicleInfo();
		assertEquals(driverDtoList.size(), 4);
	}

	// test method for getDriverDtoById()
	@Test
	void testGetDriverDtoById() {
		List<DriverDto> driverDtoList = driverService.getDriverVehicleById(12);
		assertEquals(driverDtoList.size(), 1);
		assertEquals(driverDtoList.get(0).getFirstName(), "Salman");
		assertEquals(driverDtoList.get(0).getLastName(), "S");
		assertEquals(driverDtoList.get(0).getChargesPerDay(), 1200);
		assertEquals(driverDtoList.get(0).getVehicleNumber(), "CG07BX5333");
		assertEquals(driverDtoList.get(0).getDescription(), "swift in good condition");
	}
}
