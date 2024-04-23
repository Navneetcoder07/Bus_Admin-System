package org.jsp.adminBus.service;

import java.util.Optional;

import org.jsp.adminBus.dao.AdminDao;
import org.jsp.adminBus.dto.Admin;
import org.jsp.adminBus.dto.ResponseStructure;
import org.jsp.adminBus.exception.LoginInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	ResponseStructure<Admin> structure=new ResponseStructure<>();
	
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin){
		structure.setData(adminDao.saveAdmin(admin));
		structure.setMessage("Admin saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin){
		Optional<Admin> dbOptional=adminDao.findById(admin.getId());
		if(dbOptional.isPresent()) {
			Admin dbAdmin=dbOptional.get();
			dbAdmin.setName(admin.getName());
			dbAdmin.setEmail(admin.getEmail());
			dbAdmin.setGst(admin.getGst());
			dbAdmin.setPhone(admin.getPhone());
			dbAdmin.setPassword(admin.getPassword());
			dbAdmin.setBus(admin.getBus());
			dbAdmin.setTravels_name(admin.getTravels_name());
			structure.setData(adminDao.saveAdmin(admin));
			structure.setMessage("Admin updated successfully");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.ACCEPTED);
		}
		throw new LoginInvalidException("Id Not Present");
	}
	
	public ResponseEntity<ResponseStructure<Admin>> loginByPhoneAndPassword(long phone,String password){
		Optional<Admin> dbOptional=adminDao.loginByPhoneAndPassword(phone, password);
		if(dbOptional.isPresent()) {
			structure.setMessage("Login Successful");
			structure.setData(dbOptional.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.OK);
		}
		throw new LoginInvalidException("Wrong Phone and Password");
	}
	
	public ResponseEntity<ResponseStructure<Admin>> loginByEmailAndPassword(String email,String password){
		Optional<Admin> dbOptional=adminDao.loginByEmailAndPassword(email, password);
		if(dbOptional.isPresent()) {
			structure.setMessage("Login Successful");
			structure.setData(dbOptional.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.OK);
		}
		throw new LoginInvalidException("Wrong Email and Password");
	}
	
	public ResponseEntity<ResponseStructure<Admin>> findAdminById(int id){
		Optional<Admin> dbOptional=adminDao.findById(id);
		if(dbOptional.isPresent()) {
			structure.setData(dbOptional.get());
			structure.setMessage("Finding successful");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.OK);
		}
		throw new LoginInvalidException("Invalid Id");
	}

}
