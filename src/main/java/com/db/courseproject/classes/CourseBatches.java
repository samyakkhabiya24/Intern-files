package com.db.courseproject.classes;

import java.time.LocalDate;

public class CourseBatches {
    
    private Long id;
    private Long courseId;
    private String batchName;
    private LocalDate batchStartDate;
    private LocalDate batchEndDate;
    private String batchTiming;
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public LocalDate getBatchStartDate() {
		return batchStartDate;
	}
	public void setBatchStartDate(LocalDate batchStartDate) {
		this.batchStartDate = batchStartDate;
	}
	public LocalDate getBatchEndDate() {
		return batchEndDate;
	}
	public void setBatchEndDate(LocalDate batchEndDate) {
		this.batchEndDate = batchEndDate;
	}
	public String getBatchTiming() {
		return batchTiming;
	}
	public void setBatchTiming(String batchTiming) {
		this.batchTiming = batchTiming;
	}
	
    
//    public Long getId() {
//        return id;
//    }
//    
//    public void setId(Long id) {
//        this.id = id;
//    }
//    
//    public Long getCourseId() {
//        return courseId;
//    }
//    
//    public void setCourseId(Long courseId) {
//        this.courseId = courseId;
//    }
//    
//    public String getBatchName() {
//        return batchName;
//    }
//    
//    public void setBatchName(String batchName) {
//        this.batchName = batchName;
//    }
//    
//    public LocalDate getBatchStartDate() {
//        return batchStartDate;
//    }
//    
//    public void setBatchStartDate(LocalDate batchStartDate) {
//        this.batchStartDate = batchStartDate;
//    }
//    
//    public LocalDate getBatchEndDate() {
//        return batchEndDate;
//    }
//    
//    public void setBatchEndDate(LocalDate batchEndDate) {
//        this.batchEndDate = batchEndDate;
//    }
//    
//    public String getBatchTiming() {
//        return batchTiming;
//    }
//    
//    public void setBatchTiming(String batchTiming) {
//        this.batchTiming = batchTiming;
//    }
    
}
