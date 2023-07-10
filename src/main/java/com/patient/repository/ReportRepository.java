package com.patient.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.entity.Report;
import com.patient.entity.Patient;

public interface ReportRepository extends JpaRepository<Report, Long> {
    // You can add custom query methods if needed
	 List<Report> findByPatient(Patient patient);
	
}
