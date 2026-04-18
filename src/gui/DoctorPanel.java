package gui;

import model.Doctor;
import service.DoctorService;
import util.DiseaseCatalog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DoctorPanel extends JPanel {
    private final DoctorService doctorService;
    private final Runnable onDoctorAdded;
    private final JTextField idField;
    private final JTextField nameField;
    private final JTextField ageField;
    private final JComboBox<String> genderComboBox;
    private final JComboBox<String> specializationComboBox;
    private final JTextField availableTimeField;
    private final DefaultTableModel tableModel;

    public DoctorPanel(DoctorService doctorService) {
        this(doctorService, null);
    }

    public DoctorPanel(DoctorService doctorService, Runnable onDoctorAdded) {
        this.doctorService = doctorService;
        this.onDoctorAdded = onDoctorAdded;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(16, 16, 16, 16));
        setBackground(new Color(245, 248, 252));

        JPanel formPanel = new JPanel(new BorderLayout(0, 12));
        formPanel.setBorder(new TitledBorder("Register Doctor"));
        formPanel.setBackground(Color.WHITE);

        JPanel fieldsPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        fieldsPanel.setBackground(Color.WHITE);

        idField = new JTextField();
        idField.setEditable(false);
        nameField = new JTextField();
        ageField = new JTextField();
        genderComboBox = new JComboBox<>(new String[] { "Male", "Female" });
        specializationComboBox = new JComboBox<>(DiseaseCatalog.getDoctorSpecializations().toArray(new String[0]));
        availableTimeField = new JTextField();
        updateNextDoctorId();

        fieldsPanel.add(new JLabel("ID:"));
        fieldsPanel.add(idField);
        fieldsPanel.add(new JLabel("Name:"));
        fieldsPanel.add(nameField);
        fieldsPanel.add(new JLabel("Age:"));
        fieldsPanel.add(ageField);
        fieldsPanel.add(new JLabel("Gender:"));
        fieldsPanel.add(genderComboBox);
        fieldsPanel.add(new JLabel("Specialization:"));
        fieldsPanel.add(specializationComboBox);
        fieldsPanel.add(new JLabel("Available Time:"));
        fieldsPanel.add(availableTimeField);

        JButton addButton = new JButton("Add Doctor");

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        actionPanel.setBackground(Color.WHITE);
        actionPanel.add(addButton);

        formPanel.add(fieldsPanel, BorderLayout.CENTER);
        formPanel.add(actionPanel, BorderLayout.SOUTH);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new String[] { "ID", "Name", "Age", "Gender", "Specialization", "Available Time" }, 0);
        JTable table = new JTable(tableModel);
        table.setRowHeight(24);
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new TitledBorder("Doctor Records"));
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> addDoctor());
    }

    private void addDoctor() {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = (String) genderComboBox.getSelectedItem();
            String specialization = (String) specializationComboBox.getSelectedItem();
            String availableTime = availableTimeField.getText();

            Doctor doctor = doctorService.createDoctor(name, age, gender, specialization, availableTime);

            tableModel.addRow(new Object[] {
                    doctor.getId(),
                    doctor.getName(),
                    doctor.getAge(),
                    doctor.getGender(),
                    doctor.getSpecialization(),
                    doctor.getAvailableTime()
            });
            clearFields();
            if (onDoctorAdded != null) {
                onDoctorAdded.run();
            }
            JOptionPane.showMessageDialog(this, "Doctor added successfully");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input");
        }
    }

    private void clearFields() {
        updateNextDoctorId();
        nameField.setText("");
        ageField.setText("");
        genderComboBox.setSelectedIndex(0);
        specializationComboBox.setSelectedIndex(0);
        availableTimeField.setText("");
        nameField.requestFocusInWindow();
    }

    private void updateNextDoctorId() {
        idField.setText(String.valueOf(doctorService.getNextDoctorId()));
    }
}
