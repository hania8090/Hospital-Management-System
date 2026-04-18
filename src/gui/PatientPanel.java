package gui;

import model.Patient;
import service.PatientService;
import util.DiseaseCatalog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PatientPanel extends JPanel {
    private final PatientService patientService;
    private final Runnable onPatientAdded;
    private final JTextField idField;
    private final JTextField nameField;
    private final JTextField ageField;
    private final JComboBox<String> genderComboBox;
    private final JComboBox<String> diseaseComboBox;
    private final JTextField contactField;
    private final DefaultTableModel tableModel;

    public PatientPanel(PatientService patientService) {
        this(patientService, null);
    }

    public PatientPanel(PatientService patientService, Runnable onPatientAdded) {
        this.patientService = patientService;
        this.onPatientAdded = onPatientAdded;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(16, 16, 16, 16));
        setBackground(new Color(245, 248, 252));

        JPanel formPanel = new JPanel(new BorderLayout(0, 12));
        formPanel.setBorder(new TitledBorder("Register Patient"));
        formPanel.setBackground(Color.WHITE);

        JPanel fieldsPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        fieldsPanel.setBackground(Color.WHITE);

        idField = new JTextField();
        idField.setEditable(false);
        nameField = new JTextField();
        ageField = new JTextField();
        genderComboBox = new JComboBox<>(new String[] { "Male", "Female" });
        diseaseComboBox = new JComboBox<>(DiseaseCatalog.getStartupDiseases().toArray(new String[0]));
        diseaseComboBox.setEditable(true);
        contactField = new JTextField();
        updateNextPatientId();

        fieldsPanel.add(new JLabel("ID:"));
        fieldsPanel.add(idField);
        fieldsPanel.add(new JLabel("Name:"));
        fieldsPanel.add(nameField);
        fieldsPanel.add(new JLabel("Age:"));
        fieldsPanel.add(ageField);
        fieldsPanel.add(new JLabel("Gender:"));
        fieldsPanel.add(genderComboBox);
        fieldsPanel.add(new JLabel("Disease:"));
        fieldsPanel.add(diseaseComboBox);
        fieldsPanel.add(new JLabel("Contact:"));
        fieldsPanel.add(contactField);

        JButton addButton = new JButton("Add Patient");

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        actionPanel.setBackground(Color.WHITE);
        actionPanel.add(addButton);

        formPanel.add(fieldsPanel, BorderLayout.CENTER);
        formPanel.add(actionPanel, BorderLayout.SOUTH);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[] { "ID", "Name", "Age", "Gender", "Disease", "Contact" }, 0);
        JTable table = new JTable(tableModel);
        table.setRowHeight(24);
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new TitledBorder("Patient Records"));
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> addPatient());
    }

    private void addPatient() {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = (String) genderComboBox.getSelectedItem();
            String disease = String.valueOf(diseaseComboBox.getSelectedItem());
            String contact = contactField.getText();

            Patient patient = patientService.createPatient(name, age, gender, disease, contact);

            tableModel.addRow(new Object[] {
                    patient.getId(),
                    patient.getName(),
                    patient.getAge(),
                    patient.getGender(),
                    patient.getDisease(),
                    patient.getContact()
            });
            clearFields();
            if (onPatientAdded != null) {
                onPatientAdded.run();
            }
            JOptionPane.showMessageDialog(this, "Patient added successfully");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input");
        }
    }

    private void clearFields() {
        updateNextPatientId();
        nameField.setText("");
        ageField.setText("");
        genderComboBox.setSelectedIndex(0);
        diseaseComboBox.setSelectedIndex(0);
        contactField.setText("");
        nameField.requestFocusInWindow();
    }

    private void updateNextPatientId() {
        idField.setText(String.valueOf(patientService.getNextPatientId()));
    }
}
