package org.jsp.adminBus.repository;

import java.util.Optional;

import org.jsp.adminBus.dto.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	@Query("select a from Admin a where a.phone=?1 and a.password=?2")
	Optional<Admin> loginAdmin(long phone, String password);
	
	@Query("select a from Admin a where a.email=?1 and a.password=?2")
	Optional<Admin> loginAdmin(String email,String password);

}
