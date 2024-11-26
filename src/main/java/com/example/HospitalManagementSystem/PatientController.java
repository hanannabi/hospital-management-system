package com.example.HospitalManagementSystem;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class PatientController {

    HashMap<Integer, Patient> patientDb = new HashMap<>();

    @PostMapping("/addPatientViaParameters")
    public String addPatient(@RequestParam("patientId") Integer patientId,
                             @RequestParam("name") String name,
                             @RequestParam("disease") String disease,
                             @RequestParam("age") Integer age) {


        Patient patient = new Patient(patientId, name, disease, age);
        patientDb.put(patientId, patient);
        return "patient added successfully";

    }

    @PostMapping("/addPatientViaRequestBody")
    public String addPatient(@RequestBody Patient patient) {

        int key = patient.getPatientId();
        patientDb.put(key, patient);
        return "patient added successfully";
    }

    @GetMapping("/getPatientInfo")
    public Patient getPatient(@RequestParam("patientId") Integer patientId) {

        Patient patient = patientDb.get(patientId);
        return patient;

    }

    @GetMapping("/getAllPatients")

    public List<Patient> getAllPatients() {

        List<Patient> patients = new ArrayList<>();
        for (Patient p : patientDb.values()) {
            patients.add(p);
        }
        return patients;

    }

    @GetMapping("/getPatientByName")
    public Patient getPatientByName(@RequestParam("name") String name) {
        for (Patient p : patientDb.values()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }

        return null;
    }

    @GetMapping("/getPatientsGreaterThanAge")
    public List<Patient> getAgeGreaterThan(@RequestParam("age") Integer age) {

        List<Patient> patients = new ArrayList<>();
        for (Patient p : patientDb.values()) {
            if (p.getAge() > age) {
                patients.add(p);
            }
        }
        return patients;
    }
}
