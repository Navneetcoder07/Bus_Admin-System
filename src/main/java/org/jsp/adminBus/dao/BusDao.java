package org.jsp.adminBus.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.adminBus.dto.Bus;
import org.jsp.adminBus.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BusDao {
	@Autowired
	private BusRepository busRepository;
	
	public Bus saveBus(Bus bus) {
		return busRepository.save(bus);
	}
	
	public Optional<Bus> findById(int id){
		return busRepository.findById(id);
	}
	
	public Optional<Bus> findByLd(String date_of_departure,String from_location,String to_location){
		return busRepository.findByLd(date_of_departure, from_location, to_location);
	}
	
	public Optional<Bus> findByNumber(String bus_number){
		return busRepository.findByBusNumber(bus_number);
	}
	
	public List<Bus> findByTravlesName(String travels_name){
		return busRepository.findByTravlesName(travels_name);
	}
	public List<Bus> findByDeparture(String date_of_departure){
		return busRepository.findByDeparture(date_of_departure);
	}
	public List<Bus> findByAdminId(int admin_id){
		return busRepository.findByAdminId(admin_id);
	}

}
