package com.example.HospitalManagementSystem;

public class Patient {
    private int patientId;
    private String name;
    private String disease;
    private int agge;

    public Patient(int patientId, String name, String disease, int agge) {
        this.patientId = patientId;
        this.name = name;
        this.disease = disease;
        this.agge = agge;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public int getAgge() {
        return agge;
    }

    public void setAge(int agge) {
        this.agge = agge;
    }
}
