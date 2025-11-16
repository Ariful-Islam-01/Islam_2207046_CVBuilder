package com.example.cv_builder.model;

public class CV {

    private String fullname;
    private String email;
    private String phone;
    private String address;
    private String education;
    private String skills;
    private String projects;
    private String experience;

    // Empty Constructor
    public CV() {}

    // Method to set all data at once
    public void setData(String fullname, String email, String phone, String address,
                        String education, String skills, String projects, String experience) {

        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.education = education;
        this.skills = skills;
        this.projects = projects;
        this.experience = experience;
    }

    // Getter Methods
    public String getFullname() { return fullname; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getEducation() { return education; }
    public String getSkills() { return skills; }
    public String getProjects() { return projects; }
    public String getExperience() { return experience; }

}
