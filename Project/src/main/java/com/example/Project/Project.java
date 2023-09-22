package com.example.Project;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Project {
    
    @JsonProperty("wardname")
    private List<String> wardName;

    @JsonProperty("personID")
    private String personID;
    
    @JsonProperty("firstname")
    private String firstName;
    
    @JsonProperty("lastname")
    private String lastName;
    
    @JsonProperty("fathername")
    private String fatherName;
    
    Project( List<String> wardName, String personID, String firstName, String lastName, String fatherName) {
        this.wardName = wardName;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
    }

    public List<String> getWardName() {
        return wardName;
    }

    public void setWardName(List<String> wardName) {
        this.wardName = wardName;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
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

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
}