package com.patient.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

@Entity
@Table(name = "report")
public class Report {
    @Id
  
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "test_results", length = 1000)
    private String testResults;

    @Column(name = "diagnosis", length = 1000)
    private String diagnosis;

    @Column(name = "test_type", length = 100)
    private String testType;

    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public Report() {
        // Default constructor
    }

    public Report(Long id, Patient patient, String testResults, String diagnosis, String testType, Date date) {
        this.id = id;
        this.patient = patient;
        this.testResults = testResults;
        this.diagnosis = diagnosis;
        this.testType = testType;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getTestResults() {
        return testResults;
    }

    public void setTestResults(String testResults) {
        this.testResults = testResults;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Report [id=" + id + ", patient=" + patient + ", testResults=" + testResults + ", diagnosis=" + diagnosis
                + ", testType=" + testType + ", date=" + date + "]";
    }
    
   
}
