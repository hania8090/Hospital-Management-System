package service;

import model.Patient;
import java.util.ArrayList;

public class PatientService {
    private final ArrayList<Patient> patients = new ArrayList<>();
    private int nextPatientId = 1;

    public int getNextPatientId() {
        return nextPatientId;
    }

    public Patient createPatient(String name, int age, String gender, String disease, String contact) {
        Patient patient = new Patient(nextPatientId++, name, age, gender, disease, contact);
        patients.add(patient);
        return patient;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
        nextPatientId = Math.max(nextPatientId, patient.getId() + 1);
    }

    public ArrayList<Patient> getAllPatients() {
        return patients;
    }

    public Patient searchPatientById(int id) {
        for (Patient p : patients) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean deletePatient(int id) {
        Patient patient = searchPatientById(id);
        if (patient != null) {
            patients.remove(patient);
            return true;
        }
        return false;
    }
}
