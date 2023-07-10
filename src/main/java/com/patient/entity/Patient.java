package com.patient.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "patient_info")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id")
	private Long id;
	
	@Column(name = "f_name", length = 50, nullable = false)
	private String firstName;
	
	@Column(name = "l_name", length = 50)
	private String lastName;
	
	private String gender;
	
	@Column(length = 50)
	private String city;
	
	private String email;
	
	@Column(length = 13)
	private Long mobile;
	private String medicalHistory;
	
	@Column(length = 6)
	private Long feesPaid;

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(String firstName, String lastName, String gender, String city, String email, Long mobile,
			String medicalHistory, Long feesPaid) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.city = city;
		this.email = email;
		this.mobile = mobile;
		this.medicalHistory = medicalHistory;
		this.feesPaid = feesPaid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public Long getFeesPaid() {
		return feesPaid;
	}

	public void setFeesPaid(Long FeesPaid) {
		this.feesPaid = FeesPaid;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", city=" + city + ", email=" + email + ", mobile=" + mobile + ", medical History =" + medicalHistory
				+ ", Fees Paid=" + feesPaid + "]";
	}

	public void setTestResults(String testResults) {
		// TODO Auto-generated method stub
		
	}

	public void setDiagnosis(String diagnosis) {
		// TODO Auto-generated method stub
		
	}

	 
}
