package com.db.courseproject.classes;

public class ContactInfo {
	private int id;
	

	private String parentName;
    private String yourName;
    private String email;
    private String mobile;
    private String yourQuery;

    public String getYourQuery() {
		return yourQuery;
	}

	public void setYourQuery(String yourQuery) {
		this.yourQuery = yourQuery;
	}

	// Add getters and setters
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getYourName() {
        return yourName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    
}
