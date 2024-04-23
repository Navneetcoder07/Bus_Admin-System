package org.jsp.adminBus.controller;

import org.jsp.adminBus.dto.Admin;
import org.jsp.adminBus.dto.ResponseStructure;
import org.jsp.adminBus.service.AdminService;
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
@RequestMapping("/admins")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@RequestBody Admin admin){
		return adminService.saveAdmin(admin);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@RequestBody Admin admin){
		return adminService.updateAdmin(admin);
	}
	
	@GetMapping("/login-by-phone")
	public ResponseEntity<ResponseStructure<Admin>> findByPhoneAndPassword(@RequestParam long phone,@RequestParam String password){
		return adminService.loginByPhoneAndPassword(phone, password);
	}
	@GetMapping("/login-by-email")
	public ResponseEntity<ResponseStructure<Admin>> findByEmailAndPassword(@RequestParam String email,@RequestParam String password){
		return adminService.loginByEmailAndPassword(email, password);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Admin>> findById(@PathVariable int id){
		return adminService.findAdminById(id);
	}

}
