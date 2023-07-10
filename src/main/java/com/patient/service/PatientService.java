package com.patient.service;

import java.util.List;


import com.patient.entity.Patient;
import com.patient.entity.Report;
public interface PatientService {
	List<Patient> getAllPatients();
	Patient savePatients(Patient patient);
	
	Patient getPatientById(Long id);
	Patient updatePatients(Patient patient);
	List<Patient> searchPatients(String keyword);

	
	void deletePatients(Long id);
	Report saveReport(Report report);
	

	 

}
