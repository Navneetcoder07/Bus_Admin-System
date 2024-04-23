package org.jsp.adminBus.controller;

import java.util.List;

import org.jsp.adminBus.dto.Bus;
import org.jsp.adminBus.dto.ResponseStructure;
import org.jsp.adminBus.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buses")
public class BusController {
	
	@Autowired
	private BusService busService;
	
	@PostMapping("/{admin_id}")
	public ResponseEntity<ResponseStructure<Bus>> saveBus(@RequestBody Bus bus,@PathVariable int admin_id){
		return busService.saveBus(bus,admin_id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Bus>> updateBus(@RequestBody Bus bus){
		return busService.updateBus(bus);
	}
	
	@GetMapping("/find-by-Ld")
	public ResponseEntity<ResponseStructure<Bus>> findByLd(@RequestParam String date_of_departure,@RequestParam String from_location,@RequestParam String to_location){
		return busService.findByLd(date_of_departure, from_location, to_location);
	}
	
	@GetMapping("/{bus_number}")
	public ResponseEntity<ResponseStructure<Bus>> findByBusNumber(@PathVariable String bus_number){
		return busService.findByBusNumber(bus_number);
	}
	@GetMapping("/travel")
	public ResponseEntity<ResponseStructure<List<Bus>>> findByTravlesName(@RequestParam String travels_name){
		return busService.findByTravlesName(travels_name);
	}
	@GetMapping("/date")
	public ResponseEntity<ResponseStructure<List<Bus>>> findByDeparture(@RequestParam String date_of_departure){
		return busService.findByDeparture(date_of_departure);
	}
	@GetMapping("/adminId")
	public ResponseEntity<ResponseStructure<List<Bus>>> findByAdminId(@RequestParam int admin_id){
		return busService.findByAdminId(admin_id);
	}

}
