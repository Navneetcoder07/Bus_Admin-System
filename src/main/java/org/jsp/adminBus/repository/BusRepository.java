package org.jsp.adminBus.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.adminBus.dto.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BusRepository extends JpaRepository<Bus, Integer> {
	@Query("select b from Bus b where b.date_of_departure=?1 and b.from_location=?2 and b.to_location=?3")
	Optional<Bus> findByLd(String date_of_departure,String from_location,String to_location);
	
	@Query("select b from Bus b where b.bus_number=?1")
	Optional<Bus> findByBusNumber(String bus_number);
	
	@Query("select b from Bus b where b.admin.travels_name=?1")
	List<Bus> findByTravlesName(String travels_name);
	
	@Query("select b from Bus b where b.date_of_departure=?1")
	List<Bus> findByDeparture(String date_of_departure);
	@Query("select b from Bus b where b.admin.id=?1")
	List<Bus> findByAdminId(int admin_id);

}
