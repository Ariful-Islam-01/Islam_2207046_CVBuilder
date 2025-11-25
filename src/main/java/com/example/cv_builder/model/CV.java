package com.example.cv_builder.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CV {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String education;
    private String skills;
    private String projects;
    private String experience;

    public CV() {}

    @JsonCreator
    public CV(@JsonProperty("id") Integer id,
              @JsonProperty("name") String name,
              @JsonProperty("email") String email,
              @JsonProperty("phone") String phone,
              @JsonProperty("address") String address,
              @JsonProperty("education") String education,
              @JsonProperty("skills") String skills,
              @JsonProperty("projects") String projects,
              @JsonProperty("experience") String experience) {
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

    public CV(String name, String email, String phone, String address,
              String education, String skills, String projects, String experience) {
        this(null, name, email, phone, address, education, skills, projects, experience);
    }

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
        if (!(o instanceof CV)) return false;
        CV cv = (CV) o;
        return Objects.equals(id, cv.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
