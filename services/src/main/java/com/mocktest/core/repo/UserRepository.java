package com.mocktest.core.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.mocktest.core.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	Optional<User> findByEmailId(String emailId);
	void deleteByEmailId(String emailId);
	@Query("from User u where u.admin=false")
	List<User> findAllUsers();
	
	List<User> findByEmailIdContainingIgnoreCaseAndAdmin(String emailId,boolean admin);
}
