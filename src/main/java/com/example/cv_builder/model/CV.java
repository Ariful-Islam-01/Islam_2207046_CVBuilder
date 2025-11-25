package com.example.cv_builder.model;

import java.util.Objects;

public class CV {
    private Integer id; // null when not stored yet
    private String name, email, phone, address;
    private String education, skills, projects, experience;

    public CV() {}

    public CV(Integer id, String name, String email, String phone, String address,
              String education, String skills, String projects, String experience) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.education = education;
        this.skills = skills;
        this.projects = projects;
        this.experience = experience;
    }

    // convenience constructor without id
    public CV(String name, String email, String phone, String address,
              String education, String skills, String projects, String experience) {
        this(null, name, email, phone, address, education, skills, projects, experience);
    }

    // getters & setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public String getProjects() { return projects; }
    public void setProjects(String projects) { this.projects = projects; }

    public String getExperience() { return experience; }
    public void setExperience(String experience) { this.experience = experience; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CV cv = (CV) o;
        return Objects.equals(id, cv.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
