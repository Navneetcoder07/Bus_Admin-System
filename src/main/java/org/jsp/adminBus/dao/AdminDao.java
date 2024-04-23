package org.jsp.adminBus.dao;

import java.util.Optional;

import org.jsp.adminBus.dto.Admin;
import org.jsp.adminBus.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class AdminDao {
	
	@Autowired
	private AdminRepository adminRepository;
	
	public Admin saveAdmin(Admin admin){
		return adminRepository.save(admin);
	}
	public Admin updateAdmin(Admin admin){
		return adminRepository.save(admin);
	}
	public Optional<Admin> findById(int id){
		return adminRepository.findById(id);
	}
	
	public Optional<Admin> loginByPhoneAndPassword(long phone,String password){
		return adminRepository.loginAdmin(phone, password);
	}
	public Optional<Admin> loginByEmailAndPassword(String email,String password){
		return adminRepository.loginAdmin(email, password);
	}
	
	

}
