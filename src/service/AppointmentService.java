package service;

import model.Appointment;
import model.Doctor;
import model.Patient;
import java.util.ArrayList;

public class AppointmentService {
    private final ArrayList<Appointment> appointments = new ArrayList<>();
    private int nextAppointmentId = 1;

    public int getNextAppointmentId() {
        return nextAppointmentId;
    }

    public Appointment createAppointment(Patient patient, Doctor doctor, String date, String time) {
        Appointment appointment = new Appointment(nextAppointmentId++, patient, doctor, date, time);
        appointments.add(appointment);
        return appointment;
    }

    public void bookAppointment(Appointment appointment) {
        appointments.add(appointment);
        nextAppointmentId = Math.max(nextAppointmentId, appointment.getAppointmentId() + 1);
    }

    public ArrayList<Appointment> getAllAppointments() {
        return appointments;
    }

    public boolean cancelAppointment(int appointmentId) {
        for (Appointment a : appointments) {
            if (a.getAppointmentId() == appointmentId) {
                appointments.remove(a);
                return true;
            }
        }
        return false;
    }
}
