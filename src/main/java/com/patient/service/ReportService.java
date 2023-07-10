package com.patient.service;

import java.util.List;
import java.util.Optional;

import com.patient.entity.Patient;
import com.patient.entity.Report;

public interface ReportService {
    Report saveReport(Report report);
    // Add other methods for retrieving, updating, and deleting reports if needed

    List<Report> getReportsByPatient(Patient patient);
    
    Optional<Report> getReportById(Long id);
    void deleteReport(Report report);

	

	void updateReport(Report existingReport);


	Report getReportByPatient(Patient existingPatient);

	static void deleteReportsByPatientId(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
