package com.patient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.patient.entity.Patient;
import com.patient.repository.PatientRepository;

@SpringBootApplication
public class PatientApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}

	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	public void run(String... args) throws Exception {

	}

}
