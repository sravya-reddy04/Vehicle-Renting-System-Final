package com.vrs.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

import com.vrs.dao.IDriverRepository;
import com.vrs.dto.DriverDto;
import com.vrs.entities.Driver;
import com.vrs.entities.Vehicle;

@ExtendWith(SpringExtension.class)
public class DriverServiceMockitoTest {
	// @InjectMock - injects EmployeeService and inject dependent classes/interfaces
	// that are annotated with @Mock
	@InjectMocks
	DriverServiceImpl driverService;

	// @MockBean - injecting external services

	@MockBean
	IDriverRepository driverRepository;

	// Initialization of mock objects
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	// test method for getDriverById()
	@Test
	void testGetDriverById() {
		Driver driver = new Driver();
		driver.setAddress("bhilai chhattisgarh");
		driver.setChargesPerDay(1000);
		driver.setContactNumber("9123456789");
		driver.setDriverId(25);
		driver.setEmail("samir@capgemini.com");
		driver.setFirstName("samir");
		driver.setLastName("singh");
		driver.setLicenseNo("CG07 20007777777");

		// mocking repository
		Mockito.when(driverRepository.findById(25)).thenReturn(Optional.of(driver));

		// Validating the response
		Driver response = driverService.getDriverById(25);
		assertEquals("samir", response.getFirstName());
		assertEquals("singh", response.getLastName());
		assertEquals("CG07 20007777777", response.getLicenseNo());
		assertEquals("samir@capgemini.com", response.getEmail());
		assertEquals("9123456789", response.getContactNumber());
		assertEquals("bhilai chhattisgarh", response.getAddress());
		assertEquals(25, response.getDriverId());
		assertEquals(1000, response.getChargesPerDay());
	}

	// test method for getAllDrivers()
	@Test
	void testGetAllDrivers() {
		List<Driver> drivers1 = new ArrayList<Driver>(3);
		drivers1.add(new Driver());
		drivers1.add(new Driver());
		drivers1.add(new Driver());
		Mockito.when(driverRepository.findAll()).thenReturn(drivers1);
		List<Driver> drivers2 = driverService.getAllDrivers();
		assertEquals(drivers2.size(), 3);
	}

	// test method for getAllDriverDto()
	@Test
	void testGetAllDriverDto() {
		List<DriverDto> driverDto1 = new ArrayList<DriverDto>(4);
		driverDto1.add(new DriverDto());
		driverDto1.add(new DriverDto());
		driverDto1.add(new DriverDto());
		driverDto1.add(new DriverDto());
		Mockito.when(driverRepository.getAllDriverVehicleInfo()).thenReturn(driverDto1);
		List<DriverDto> driverDto2 = driverService.getAllDriverVehicleInfo();
		assertEquals(driverDto2.size(), 4);
	}

	// test method for getDriverDtoById()
	@Test
	void testGetDriverDtoById() {
		List<DriverDto> driverDto1 = new ArrayList<DriverDto>();
		driverDto1.add(new DriverDto("Salman", "S", "9039460640", 1200, "CG07BX5333", "Car", "AC",
				"swift in good condition", 200, 500));
		Mockito.when(driverRepository.getDriverVehicleInfoById(12)).thenReturn(driverDto1);
		List<DriverDto> driverDto2 = driverService.getDriverVehicleById(12);
		assertEquals(driverDto2.get(0).getFirstName(), "Salman");
		assertEquals(driverDto2.get(0).getLastName(), "S");
		assertEquals(driverDto2.get(0).getChargesPerDay(), 1200);
		assertEquals(driverDto2.get(0).getVehicleNumber(), "CG07BX5333");
		assertEquals(driverDto2.get(0).getDescription(), "swift in good condition");
	}

	// test method for updateDriverEmail()
	@Test
	void testUpdateDriverEmail() {
		Driver driver0 = new Driver();
		driver0.setEmail("samir@capgemini.com");
		Driver driver1 = new Driver();
		driver1.setEmail("singh.samir@capgemini.com");
		Mockito.when(driverRepository.findById(26)).thenReturn(Optional.of(driver0));
		Mockito.when(driverRepository.save(driver0)).thenReturn(driver1);
		Driver driver2 = driverService.updateDriverEmail(26, "singh.samir@capgemini.com");
		assertEquals(driver2.getEmail(), "singh.samir@capgemini.com");
	}

	// test method for updateDriverContactNumber()
	@Test
	void testUpdateDriverContactNumber() {
		Driver driver0 = new Driver();
		driver0.setContactNumber("9123456789");
		Driver driver1 = new Driver();
		driver1.setContactNumber("9999999999");
		Mockito.when(driverRepository.findById(26)).thenReturn(Optional.of(driver0));
		Mockito.when(driverRepository.save(driver0)).thenReturn(driver1);
		Driver driver2 = driverService.updateDriverContactNumber(26, "9999999999");
		assertEquals(driver2.getContactNumber(), "9999999999");
	}

	// test method for updateDriver()
	@Test
	void testUpdateDriver() {
		Driver driver0 = new Driver();
		driver0.setAddress("bhilai chhattisgarh");
		driver0.setChargesPerDay(1000);
		driver0.setContactNumber("9123456789");
		driver0.setDriverId(25);
		driver0.setEmail("samir@capgemini.com");
		driver0.setFirstName("samir");
		driver0.setLastName("singh");
		driver0.setLicenseNo("CG07 20007777777");
		Driver driver1 = new Driver();
		driver1.setAddress("bhilai chhattisgarh");
		driver1.setChargesPerDay(1000);
		driver1.setContactNumber("9123456789");
		driver1.setDriverId(25);
		driver1.setEmail("sameer@capgemini.com");
		driver1.setFirstName("sameer");
		driver1.setLastName("singh");
		driver1.setLicenseNo("CG07 20007777777");
		Mockito.when(driverRepository.findById(25)).thenReturn(Optional.of(driver0));
		Mockito.when(driverRepository.save(driver0)).thenReturn((driver1));
		Driver response = driverService.updateDriver(driver1);
		assertEquals("sameer", response.getFirstName());
		assertEquals("singh", response.getLastName());
		assertEquals("CG07 20007777777", response.getLicenseNo());
		assertEquals("sameer@capgemini.com", response.getEmail());
		assertEquals("9123456789", response.getContactNumber());
		assertEquals("bhilai chhattisgarh", response.getAddress());
		assertEquals(25, response.getDriverId());
		assertEquals(1000, response.getChargesPerDay());
	}

	// test method for deleteDriver()
	@Test
	void testDeleteDriverById() {
		Driver driver0 = new Driver();
		driver0.setAddress("bhilai chhattisgarh");
		driver0.setChargesPerDay(1000);
		driver0.setContactNumber("9123456789");
		driver0.setDriverId(25);
		driver0.setEmail("samir@capgemini.com");
		driver0.setFirstName("samir");
		driver0.setLastName("singh");
		driver0.setLicenseNo("CG07 20007777777");
//		Mockito.when(driverRepository.deleteById(25)).thenReturn(d);
//		Driver response = driverService.deleteDriverById(25);
//		assertEquals("samir",response.getFirstName());
//		assertEquals("singh",response.getLastName());
//		assertEquals("CG07 20007777777",response.getLicenseNo());
//		assertEquals("samir@capgemini.com",response.getEmail());
//		assertEquals("9123456789",response.getContactNumber());
//		assertEquals("bhilai chhattisgarh",response.getAddress());
//		assertEquals(25,response.getDriverId());
//		assertEquals(1000,response.getChargesPerDay());
	}

	// test method for GetDriverOrderByName()
	@Test
	void testDriverOrderByName() {
		Driver driver0 = new Driver();
		driver0.setAddress("bhilai chhattisgarh");
		driver0.setChargesPerDay(1000);
		driver0.setContactNumber("9123456789");
		driver0.setDriverId(25);
		driver0.setEmail("samir@capgemini.com");
		driver0.setFirstName("samir");
		driver0.setLastName("singh");
		driver0.setLicenseNo("CG07 20007777777");

		Driver driver1 = new Driver();
		driver1.setAddress("bhilai chhattisgarh");
		driver1.setChargesPerDay(1200);
		driver1.setContactNumber("9039460789");
		driver1.setDriverId(35);
		driver1.setEmail("alex@capgemini.com");
		driver1.setFirstName("alex");
		driver1.setLastName("geroge");
		driver1.setLicenseNo("CG07 19992015361");

		List<Driver> drivers = new ArrayList<Driver>();
		drivers.add(driver0);
		drivers.add(driver1);

		Mockito.when(driverRepository.getAllDriversOrderByName()).thenReturn(drivers);
		Collections.sort(drivers, (Driver d1, Driver d2) -> (d1.getFirstName().compareTo(d2.getFirstName())));
		assertEquals(drivers.get(0).getFirstName(), "alex");
		assertEquals(drivers.get(1).getFirstName(), "samir");
	}

	// test method for GetDriverOrderBycharge()
	@Test
	void testDriverOrderByCharge() {
		Driver driver0 = new Driver();
		driver0.setAddress("bhilai chhattisgarh");
		driver0.setChargesPerDay(1000);
		driver0.setContactNumber("9123456789");
		driver0.setDriverId(25);
		driver0.setEmail("samir@capgemini.com");
		driver0.setFirstName("samir");
		driver0.setLastName("singh");
		driver0.setLicenseNo("CG07 20007777777");

		Driver driver1 = new Driver();
		driver1.setAddress("bhilai chhattisgarh");
		driver1.setChargesPerDay(1200);
		driver1.setContactNumber("9039460789");
		driver1.setDriverId(35);
		driver1.setEmail("alex@capgemini.com");
		driver1.setFirstName("alex");
		driver1.setLastName("geroge");
		driver1.setLicenseNo("CG07 19992015361");

		List<Driver> drivers = new ArrayList<Driver>();
		drivers.add(driver0);
		drivers.add(driver1);

		Mockito.when(driverRepository.getAllDriversOrderByCharges()).thenReturn(drivers);
		Collections.sort(drivers, (Driver d1, Driver d2) -> (int) (d1.getChargesPerDay() - (d2.getChargesPerDay())));
		assertEquals(drivers.get(0).getChargesPerDay(), 1000);
		assertEquals(drivers.get(1).getChargesPerDay(), 1200);
	}

	// test method for getACVehicleDrivers()
	@Test
	void testGetACVehicleDriver() {
		Driver driver0 = new Driver();
		driver0.setAddress("bhilai chhattisgarh");
		driver0.setChargesPerDay(1000);
		driver0.setContactNumber("9123456789");
		driver0.setDriverId(25);
		driver0.setEmail("samir@capgemini.com");
		driver0.setFirstName("samir");
		driver0.setLastName("singh");
		driver0.setLicenseNo("CG07 20007777777");
		Vehicle vehicle = new Vehicle();
		vehicle.setCategory("AC");
		driver0.setVehicle(vehicle);
		List<Driver> drivers = new ArrayList<Driver>();
		drivers.add(driver0);
		Mockito.when(driverRepository.getACDrivers()).thenReturn(drivers);
		List<Driver> response = driverService.getACDriver();
		assertEquals(response.get(0).getVehicle().getCategory(), "AC");
	}

	// test Method for getDriverCapacity()
	@Test
	void testGetCapacityDriver() {
		Driver driver0 = new Driver();
		driver0.setAddress("bhilai chhattisgarh");
		driver0.setChargesPerDay(1000);
		driver0.setContactNumber("9123456789");
		driver0.setDriverId(25);
		driver0.setEmail("samir@capgemini.com");
		driver0.setFirstName("samir");
		driver0.setLastName("singh");
		driver0.setLicenseNo("CG07 20007777777");
		Vehicle vehicle = new Vehicle();
		vehicle.setCapacity("4");
		driver0.setVehicle(vehicle);
		List<Driver> drivers = new ArrayList<Driver>();
		drivers.add(driver0);
		Mockito.when(driverRepository.getACDrivers()).thenReturn(drivers);
		List<Driver> response = driverService.getACDriver();
		assertTrue(Integer.parseInt(response.get(0).getVehicle().getCapacity()) >= 4);
	}

	// test method for getDriverFromSpecificLocation()
	@Test
	void testGetAllDriversFromSpecificLocation() {
		Driver driver0 = new Driver();
		driver0.setAddress("bhilai chhattisgarh");
		driver0.setChargesPerDay(1000);
		driver0.setContactNumber("9123456789");
		driver0.setDriverId(25);
		driver0.setEmail("samir@capgemini.com");
		driver0.setFirstName("samir");
		driver0.setLastName("singh");
		driver0.setLicenseNo("CG07 20007777777");
		List<Driver> drivers = new ArrayList<Driver>();
		drivers.add(driver0);
		Mockito.when(driverRepository.getAllDriversFromSpecficLocation("bhilai")).thenReturn(drivers);
		List<Driver> response = driverService.getAllDriversFromSpecficLocation("bhilai");
		assertTrue(response.get(0).getAddress().contains("bhilai"));
	}

	// test method for addDriver()
	@Test
	void testaddDriver() {
		Driver driver0 = new Driver();
		driver0.setAddress("bhilai chhattisgarh");
		driver0.setChargesPerDay(1000);
		driver0.setContactNumber("9123456789");
		driver0.setDriverId(25);
		driver0.setEmail("samir@capgemini.com");
		driver0.setFirstName("samir");
		driver0.setLastName("singh");
		driver0.setLicenseNo("CG07 20007777777");
		Mockito.when(driverRepository.save(driver0)).thenReturn(driver0);
		Driver response = driverService.addDriver(driver0);
		assertEquals("samir", response.getFirstName());
		assertEquals("singh", response.getLastName());
		assertEquals("CG07 20007777777", response.getLicenseNo());
		assertEquals("samir@capgemini.com", response.getEmail());
		assertEquals("9123456789", response.getContactNumber());
		assertEquals("bhilai chhattisgarh", response.getAddress());
		assertEquals(25, response.getDriverId());
		assertEquals(1000, response.getChargesPerDay());
	}
}
