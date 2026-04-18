package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import service.PatientService;
import service.DoctorService;
import service.AppointmentService;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Hospital Appointment System");
        setSize(1024, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        UIManager.put("TabbedPane.selected", new Color(225, 239, 255));

        PatientService patientService = new PatientService();
        DoctorService doctorService = new DoctorService();
        AppointmentService appointmentService = new AppointmentService();

        AppointmentPanel appointmentPanel = new AppointmentPanel(patientService, doctorService, appointmentService);
        PatientPanel patientPanel = new PatientPanel(patientService, appointmentPanel::refreshComboBoxes);
        DoctorPanel doctorPanel = new DoctorPanel(doctorService, appointmentPanel::refreshComboBoxes);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Patients", patientPanel);
        tabbedPane.addTab("Doctors", doctorPanel);
        tabbedPane.addTab("Appointments", appointmentPanel);
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedComponent() == appointmentPanel) {
                appointmentPanel.refreshComboBoxes();
            }
        });

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        contentPanel.setBackground(new Color(233, 240, 248));
        contentPanel.add(tabbedPane, BorderLayout.CENTER);

        add(contentPanel);
    }
}
