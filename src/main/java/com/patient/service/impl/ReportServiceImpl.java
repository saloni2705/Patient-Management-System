package com.patient.service.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.patient.entity.Patient;
import com.patient.entity.Report;
import com.patient.repository.ReportRepository;
import com.patient.service.*;

@Service
public class ReportServiceImpl implements ReportService {
    private ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

	
	@Override
	public List<Report> getReportsByPatient(Patient patient) {
	    return reportRepository.findByPatient(patient);
	}
	
	 @Override
	    public Optional<Report> getReportById(Long id) {
	        return reportRepository.findById(id);
	    }

	    @Override
	    public void deleteReport(Report report) {
	        reportRepository.delete(report);
	    }

		@Override
		public Report getReportByPatient(Patient existingPatient) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void updateReport(Report existingReport) {
			// TODO Auto-generated method stub
			
		}
		
		


   
}

