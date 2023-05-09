package com.db.courseproject.classes;

import java.math.BigDecimal;

public class CourseDetails {
	  private Long id;
	  private String courseTitle;
	  private String youtubeLink;
	  private String courseDescription;
	  private String ageGroup;
	  private Integer courseDuration;
	  private BigDecimal courseFee;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getYoutubeLink() {
		return youtubeLink;
	}
	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public Integer getCourseDuration() {
		return courseDuration;
	}
	public void setCourseDuration(Integer courseDuration) {
		this.courseDuration = courseDuration;
	}
	public BigDecimal getCourseFee() {
		return courseFee;
	}
	public void setCourseFee(BigDecimal courseFee) {
		this.courseFee = courseFee;
	}

	  // getters and setters
	}

