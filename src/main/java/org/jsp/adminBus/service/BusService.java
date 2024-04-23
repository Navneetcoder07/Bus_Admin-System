package org.jsp.adminBus.service;

import java.util.List;
import java.util.Optional;

import org.jsp.adminBus.dao.AdminDao;
import org.jsp.adminBus.dao.BusDao;
import org.jsp.adminBus.dto.Admin;
import org.jsp.adminBus.dto.Bus;
import org.jsp.adminBus.dto.ResponseStructure;
import org.jsp.adminBus.exception.InvalidBusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BusService {
	
	@Autowired
	private BusDao busDao;
	
	@Autowired
	private AdminDao adminDao;
	
	ResponseStructure<Bus> structure=new ResponseStructure<>();
	
	public ResponseEntity<ResponseStructure<Bus>> saveBus(Bus bus,int admin_id){
		Optional<Admin> db=adminDao.findById(admin_id);
		if(db.isPresent()) {
			Admin admin=db.get();
			admin.getBus().add(bus);
			bus.setAdmin(admin);
			adminDao.saveAdmin(admin);
		structure.setData(busDao.saveBus(bus));
		structure.setMessage("Bus Saved successfuly");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Bus>>(structure,HttpStatus.CREATED);
		}
		throw new InvalidBusException("Bus cant not Save with this Id");
	}
	
	public ResponseEntity<ResponseStructure<Bus>> updateBus(Bus bus){
		Optional<Bus> dbOptional=busDao.findById(bus.getId());
		if(dbOptional.isPresent()) {
			Bus dbBus=dbOptional.get();
			dbBus.setAdmin(bus.getAdmin());
			dbBus.setBus_number(bus.getBus_number());
			dbBus.setCost_per_seat(bus.getCost_per_seat());
			dbBus.setDate_of_departure(bus.getDate_of_departure());
			dbBus.setFrom_location(bus.getFrom_location());
			dbBus.setNo_of_seats(bus.getNo_of_seats());
			dbBus.setTo_location(bus.getTo_location());
			dbBus.setImage_url(bus.getImage_url());
			structure.setData(busDao.saveBus(bus));
			structure.setMessage("Bus Updated successfully");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Bus>>(structure,HttpStatus.ACCEPTED);
		}
		throw new InvalidBusException("Invalid Bus Id");
	}
	
	public ResponseEntity<ResponseStructure<Bus>> findByLd(String date_of_departure,String from_location,String to_location){
		Optional<Bus> dbOptional=busDao.findByLd(date_of_departure, from_location, to_location);
		if(dbOptional.isPresent()) {
			structure.setData(dbOptional.get());
			structure.setMessage("Finding successful");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Bus>>(structure,HttpStatus.OK);
		}
		throw new InvalidBusException("Invalid Detelis");
	}
	
	public ResponseEntity<ResponseStructure<Bus>> findByBusNumber(String bus_number){
		Optional<Bus> dbOptional=busDao.findByNumber(bus_number);
		if(dbOptional.isPresent()) {
			structure.setData(dbOptional.get());
			structure.setMessage("Finding successful");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Bus>>(structure,HttpStatus.OK);
		}
		throw new InvalidBusException("Invalid Bus Number");
	}
	
	public ResponseEntity<ResponseStructure<List<Bus>>> findByTravlesName(String travels_name){
		ResponseStructure<List<Bus>> structure=new ResponseStructure<>();
		List<Bus> dbOptional=busDao.findByTravlesName(travels_name);
		if(dbOptional.size()>0) {
			structure.setData(dbOptional);
			structure.setMessage("Finding successful");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Bus>>>(structure,HttpStatus.OK);
		}
		throw new InvalidBusException("Invalid Travles Name");
	}
	public ResponseEntity<ResponseStructure<List<Bus>>> findByDeparture(String date_of_departure){
		ResponseStructure<List<Bus>> structure=new ResponseStructure<>();
		List<Bus> dbOptional=busDao.findByDeparture(date_of_departure);
		if(dbOptional.size()>0) {
			structure.setData(dbOptional);
			structure.setMessage("Finding successful");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Bus>>>(structure,HttpStatus.OK);
		}
		throw new InvalidBusException("Invalid Departur date");
	}
	public ResponseEntity<ResponseStructure<List<Bus>>> findByAdminId(int admin_id){
		ResponseStructure<List<Bus>> structure=new ResponseStructure<>();
		List<Bus> dbOptional=busDao.findByAdminId(admin_id);
		if(dbOptional.size()>0) {
			structure.setData(dbOptional);
			structure.setMessage("Finding successful");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Bus>>>(structure,HttpStatus.OK);
		}
		throw new InvalidBusException("Invalid Admin Id");
	}

}
