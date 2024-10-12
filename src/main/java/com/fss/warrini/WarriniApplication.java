package com.fss.warrini;

import com.fss.warrini.entities.FacultyEntity;
import com.fss.warrini.repositories.FacultyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WarriniApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WarriniApplication.class, args);
	}

	@Autowired
	private FacultyRepo facultyRepo;

	@Override
	public void run(String... args) throws Exception {
		/*FacultyEntity faculty = new FacultyEntity();
		faculty.setFaculty_name("fss");
		facultyRepo.save(faculty);*/
	}
}
