package service;

import model.Doctor;
import java.util.ArrayList;

public class DoctorService {
    private final ArrayList<Doctor> doctors = new ArrayList<>();
    private int nextDoctorId = 1;

    public int getNextDoctorId() {
        return nextDoctorId;
    }

    public Doctor createDoctor(String name, int age, String gender, String specialization, String availableTime) {
        Doctor doctor = new Doctor(nextDoctorId++, name, age, gender, specialization, availableTime);
        doctors.add(doctor);
        return doctor;
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        nextDoctorId = Math.max(nextDoctorId, doctor.getId() + 1);
    }

    public ArrayList<Doctor> getAllDoctors() {
        return doctors;
    }

    public Doctor searchDoctorById(int id) {
        for (Doctor d : doctors) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    public boolean deleteDoctor(int id) {
        Doctor doctor = searchDoctorById(id);
        if (doctor != null) {
            doctors.remove(doctor);
            return true;
        }
        return false;
    }
}
