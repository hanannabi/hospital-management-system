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

    @GetMapping("/getPatientsAgeGreaterThan20HaveCold/{age}/{disease}")
    public List<Patient> getAgeGreaterThan20HaveCold(@PathVariable("/age") Integer age, @PathVariable("/disease") String disease) {
        List<Patient> patients = new ArrayList<>();
        for (Patient p : patientDb.values()) {
            if (p.getAge() > 20 && p.getDisease().equals(disease)) {
                patients.add(p);
            }
        }
        return patients;
    }

    @PutMapping("/updateDisease")
    public String updateDisease(@RequestParam("patientId") Integer patientId,@RequestParam("disease") String disease){
        if(patientDb.containsKey(patientId)){
            Patient patient = patientDb.get(patientId);   // get and set inCase of update
            patient.setDisease(disease);
            patientDb.put(patientId,patient);
            return "patient updated successfully";

        }else {
            return "patient does not exixt";
        }
    }

    @PutMapping("/updatePatientDetails")
    public String updatePatient(@RequestBody Patient patient) {
        int key = patient.getPatientId();   //use direct get
        if (patientDb.containsKey(key)) {
            patientDb.put(key, patient);
            return "patient updated successfully";
        } else {
            return "Data not existing";
        }
    }

    @DeleteMapping("/deletePatient")
    public String deletePatient(@RequestBody Patient patient) {
        int key = patient.getPatientId();
        if (patientDb.containsKey(key)) {
            patientDb.remove(key, patient);
            return "patient removed successfully";
        }
        return "data not found";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam("patientId") Integer patientId){
        patientDb.remove(patientId);
        return "patient removed";
    }
}
