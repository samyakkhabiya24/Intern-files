package com.db.courseproject.classes;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

public class Student {
	
	public enum Gender {
	    MALE("Male"),
	    FEMALE("Female");

	    private final String value;

	    Gender(String value) {
	        this.value = value;
	    }

	    public String getValue() {
	        return value;
	    }

	    public static Gender fromValue(String value) {
	        for (Gender g : Gender.values()) {
	            if (g.getValue().equalsIgnoreCase(value)) {
	                return g;
	            }
	        }
	        throw new IllegalArgumentException("No enum constant for value: " + value);
	    }
	}

	public enum PaymentMethod {
	    CASH("Cash"),
	    CHEQUE("Cheque"),
	    DEMAND_DRAFT("Demand Draft");

	    private final String value;

	    PaymentMethod(String value) {
	        this.value = value;
	    }

	    public String getValue() {
	        return value;
	    }

	    public static PaymentMethod fromValue(String value) {
	        for (PaymentMethod p : PaymentMethod.values()) {
	            if (p.getValue().equalsIgnoreCase(value)) {
	                return p;
	            }
	        }
	        throw new IllegalArgumentException("No enum constant for value: " + value);
	    }
	}
	
	

    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String resAddress;
    private String officeAddress;
    private String phoneRes;
    private String phoneOff;
    private String mobile;
    private String email;
    private String qualification;
    private String courseEnrolled;
	private PaymentMethod paymentMethod;
    private BigDecimal amount;
    private String bankName;
    private String chequeDdNo;
    private Date chequeDdDate;
    private boolean agreement;
    private Date dateApplied;
    private LocalDate startDate;
    private LocalTime startTime;
    
    
    
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getResAddress() {
		return resAddress;
	}
	public void setResAddress(String resAddress) {
		this.resAddress = resAddress;
	}
	public String getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	
	public String getPhoneRes() {
		return phoneRes;
	}
	public void setPhoneRes(String phoneRes) {
		this.phoneRes = phoneRes;
	}
	public String getPhoneOff() {
		return phoneOff;
	}
	public void setPhoneOff(String phoneOff) {
		this.phoneOff = phoneOff;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getCourseEnrolled() {
		return courseEnrolled;
	}
	public void setCourseEnrolled(String courseEnrolled) {
		this.courseEnrolled = courseEnrolled;
	}
	
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getChequeDdNo() {
		return chequeDdNo;
	}
	public void setChequeDdNo(String chequeDdNo) {
		this.chequeDdNo = chequeDdNo;
	}
	public Date getChequeDdDate() {
		return chequeDdDate;
	}
	public void setChequeDdDate(Date chequeDdDate) {
		this.chequeDdDate = chequeDdDate;
	}
	public boolean isAgreement() {
		return agreement;
	}
	public void setAgreement(boolean agreement) {
		this.agreement = agreement;
	}
	public Date getDateApplied() {
		return dateApplied;
	}
	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}
	
	
	
}