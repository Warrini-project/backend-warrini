package com.fss.warrini;

import com.fss.warrini.dto.UserDto;
import com.fss.warrini.entities.FacultyEntity;
import com.fss.warrini.entities.UserEntity;
import com.fss.warrini.repositories.FacultyRepo;
import com.fss.warrini.repositories.UserRepo;
import com.fss.warrini.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class WarriniApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WarriniApplication.class, args);
	}

	@Autowired
	private UserServices userServices;
	@Autowired
	private FacultyRepo facultyRepo;
	@Autowired
	private UserRepo userRepo;

	@Override
	public void run(String... args) throws Exception {

		// This admin account should be created just for testing
		Optional<UserEntity> userEntity = userRepo.findByUsername("ADMIN");
		if(! userEntity.isPresent()) {
			UserDto user = new UserDto();
			user.setUsername("ADMIN");
			user.setPassword("admin_fss_2025");
			Optional<FacultyEntity> facultyEntity = facultyRepo.findById(1L);
			if(! facultyEntity.isPresent()) {
				FacultyEntity faculty = new FacultyEntity();
				faculty.setFaculty_name("fss");
				facultyRepo.save(faculty);
			}
			user.setFacultyId(1L);
			userServices.addUser(user);
			UserEntity admin = userRepo.findByUsername("ADMIN").get();
			Set<String> roles = new HashSet<>();
			roles.add("ADMIN");
			user.setRoles(roles);
			admin.setRoles(roles);
			userRepo.save(admin);
		}
	}
}
