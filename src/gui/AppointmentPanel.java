package gui;

import model.Appointment;
import model.Patient;
import model.Doctor;
import service.PatientService;
import service.DoctorService;
import service.AppointmentService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentPanel extends JPanel {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("hh:mm a");

    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    private final JTextField appointmentIdField;
    private final JSpinner dateSpinner;
    private final JSpinner timeSpinner;
    private final JComboBox<Patient> patientComboBox;
    private final JComboBox<Doctor> doctorComboBox;
    private final DefaultTableModel tableModel;

    public AppointmentPanel(PatientService patientService, DoctorService doctorService,
            AppointmentService appointmentService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(16, 16, 16, 16));
        setBackground(new Color(245, 248, 252));

        JPanel formPanel = new JPanel(new BorderLayout(0, 12));
        formPanel.setBorder(new TitledBorder("Book Appointment"));
        formPanel.setBackground(Color.WHITE);

        JPanel fieldsPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        fieldsPanel.setBackground(Color.WHITE);

        appointmentIdField = new JTextField();
        appointmentIdField.setEditable(false);
        updateNextAppointmentId();

        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));

        timeSpinner = new JSpinner(new SpinnerDateModel());
        timeSpinner.setEditor(new JSpinner.DateEditor(timeSpinner, "hh:mm a"));

        patientComboBox = new JComboBox<>();
        doctorComboBox = new JComboBox<>();

        fieldsPanel.add(new JLabel("Appointment ID:"));
        fieldsPanel.add(appointmentIdField);
        fieldsPanel.add(new JLabel("Patient:"));
        fieldsPanel.add(patientComboBox);
        fieldsPanel.add(new JLabel("Doctor:"));
        fieldsPanel.add(doctorComboBox);
        fieldsPanel.add(new JLabel("Date:"));
        fieldsPanel.add(dateSpinner);
        fieldsPanel.add(new JLabel("Time:"));
        fieldsPanel.add(timeSpinner);

        JButton bookButton = new JButton("Book Appointment");

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        actionPanel.setBackground(Color.WHITE);
        actionPanel.add(bookButton);

        formPanel.add(fieldsPanel, BorderLayout.CENTER);
        formPanel.add(actionPanel, BorderLayout.SOUTH);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new String[] { "App.ID", "Patient ID", "Patient", "Doctor ID", "Doctor", "Specialization", "Date", "Time" },
                0);
        JTable table = new JTable(tableModel);
        table.setRowHeight(24);
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new TitledBorder("Scheduled Appointments"));
        add(scrollPane, BorderLayout.CENTER);

        refreshComboBoxes();

        bookButton.addActionListener(e -> bookAppointment());
    }

    public void refreshComboBoxes() {
        Patient selectedPatient = (Patient) patientComboBox.getSelectedItem();
        Doctor selectedDoctor = (Doctor) doctorComboBox.getSelectedItem();

        patientComboBox.removeAllItems();
        for (Patient p : patientService.getAllPatients()) {
            patientComboBox.addItem(p);
        }

        doctorComboBox.removeAllItems();
        for (Doctor d : doctorService.getAllDoctors()) {
            doctorComboBox.addItem(d);
        }

        if (selectedPatient != null) {
            patientComboBox.setSelectedItem(selectedPatient);
        }
        if (selectedDoctor != null) {
            doctorComboBox.setSelectedItem(selectedDoctor);
        }
    }

    private void bookAppointment() {
        try {
            Patient patient = (Patient) patientComboBox.getSelectedItem();
            Doctor doctor = (Doctor) doctorComboBox.getSelectedItem();
            String date = DATE_FORMAT.format((Date) dateSpinner.getValue());
            String time = TIME_FORMAT.format((Date) timeSpinner.getValue());

            if (patient == null || doctor == null) {
                JOptionPane.showMessageDialog(this, "Please add patients and doctors first");
                return;
            }

            Appointment appointment = appointmentService.createAppointment(patient, doctor, date, time);

            tableModel.addRow(new Object[] {
                    appointment.getAppointmentId(),
                    patient.getId(),
                    patient.getName(),
                    doctor.getId(),
                    doctor.getName(),
                    doctor.getSpecialization(),
                    date,
                    time
            });
            clearFields();
            JOptionPane.showMessageDialog(this, "Appointment booked successfully");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input");
        }
    }

    private void clearFields() {
        updateNextAppointmentId();
        dateSpinner.setValue(new Date());
        timeSpinner.setValue(new Date());
        patientComboBox.requestFocusInWindow();
    }

    private void updateNextAppointmentId() {
        appointmentIdField.setText(String.valueOf(appointmentService.getNextAppointmentId()));
    }
}
