package com.patient.controller;

import com.patient.entity.Patient;


import com.patient.entity.Report;
import com.patient.service.PatientService;
import com.patient.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.patient.entity.UserDtls;
import com.patient.service.UserService;
import java.util.List;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {
    private final PatientService patientService;
    private final ReportService reportService;

    @Autowired
    public PatientController(PatientService patientService, ReportService reportService) {
        this.patientService = patientService;
        this.reportService = reportService;
        
    }
   

    @GetMapping("/home")
    public ResponseEntity<String> home() {
    	return ResponseEntity.ok().build();
    }

    
    @GetMapping({"/patients", "/list-patients"})
    public List<Patient> listPatients() {
        return patientService.getAllPatients();
    }

    @PostMapping("/add")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.savePatients(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }

    @PostMapping("/{id}/reports")
    public ResponseEntity<Report> createReport(@PathVariable("id") Long patientId, @RequestBody Report report) {
        Patient patient = patientService.getPatientById(patientId);
        if (patient != null) {
            report.setPatient(patient);
            Report savedReport = reportService.saveReport(report);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<Patient> getPatientForUpdate(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        Patient existingPatient = patientService.getPatientById(id);
        if (existingPatient != null) {
            existingPatient.setFirstName(patient.getFirstName());
            existingPatient.setLastName(patient.getLastName());
            existingPatient.setGender(patient.getGender());
            existingPatient.setCity(patient.getCity());
            existingPatient.setEmail(patient.getEmail());
            existingPatient.setMobile(patient.getMobile());
            existingPatient.setMedicalHistory(patient.getMedicalHistory());
            existingPatient.setFeesPaid(patient.getFeesPaid());
            Patient updatedPatient = patientService.updatePatients(existingPatient);
            return ResponseEntity.ok(updatedPatient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            List<Report> reports = reportService.getReportsByPatient(patient);
            for (Report report : reports) {
                reportService.deleteReport(report);
            }
            patientService.deletePatients(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/view-report/{id}")
    public ResponseEntity<List<Report>> viewReport(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            List<Report> reports = reportService.getReportsByPatient(patient);
            return ResponseEntity.ok(reports);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Patient>> searchPatients(@RequestParam(required = false) String keyword) {
        List<Patient> searchResults = patientService.searchPatients(keyword);
        return ResponseEntity.ok(searchResults);
    }
}
