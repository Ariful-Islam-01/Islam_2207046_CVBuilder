package com.example.cv_builder.model;

public class CV {

    private static CV instance;

    private String name, email, phone, address;
    private String education, skills, projects, experience;

    public CV() {
        instance = this;
    }

    public static CV getInstance() {
        return instance;
    }

    public void setData(String name, String email, String phone, String address,
                        String education, String skills, String projects, String experience) {

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;

        this.education = education;
        this.skills = skills;
        this.projects = projects;
        this.experience = experience;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getEducation() { return education; }
    public String getSkills() { return skills; }
    public String getProjects() { return projects; }
    public String getExperience() { return experience; }
}
