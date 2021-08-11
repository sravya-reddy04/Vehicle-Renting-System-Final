package com.vrs.dao;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vrs.dto.DriverDto;
import com.vrs.entities.Driver;

public interface IDriverRepository extends CrudRepository<Driver, Integer> {

	// Get all drivers ordered by their charges per day
	@Query("select d from Driver d order by d.chargesPerDay")
	List<Driver> getAllDriversOrderByCharges();

	// Get all drivers ordered by their firstName
	@Query(value = "select * from driver order by first_name", nativeQuery = true)
	List<Driver> getAllDriversOrderByName();

	// Get drivers from a specific location
	@Query("select d from Driver d where d.address like %:location%")
	List<Driver> getAllDriversFromSpecficLocation(@Param("location") String location);

	// Get driver information along with vehicle information
	@Query(value = "select new com.vrs.dto.DriverDto(d.firstName,d.lastName,d.contactNumber,d.chargesPerDay,"
			+ "v.vehicleNumber,v.type,v.category,v.description,v.chargesPerKM,v.fixedCharges) "
			+ "from Vehicle v inner join  Driver d on v.driver=d.driverId")
	List<DriverDto> getAllDriverVehicleInfo();

	// Get driver and vehicle information with a specific booking id
	
	@Query("select new com.vrs.dto.DriverDto(d.firstName,d.lastName,d.contactNumber,d.chargesPerDay,"
			+ "v.vehicleNumber,v.type,v.category,v.description,v.chargesPerKM,v.fixedCharges) "
			+ "from Vehicle v inner join  Driver d on v.driver=d.driverId where v.booking.bookingId=:id")
	List<DriverDto> getDriverVehicleInfoById(@Param("id") int id);

	// Get drivers who drive only AC vehicles
	@Query("select d from Driver d inner join Vehicle v on v.driver=d.driverId where v.category='AC'")
	List<Driver> getACDrivers();

	// Get drivers who drive vehicle with capacity more than 4
	@Query("select d from Driver d inner join Vehicle v on v.driver=d.driverId where v.capacity!='4' and v.capacity!='2' and v.capacity!='3'")
	List<Driver> getCapacityDrivers();
}
