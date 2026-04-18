package model;

public class Patient extends Person {
    private String disease;
    private String contact;

    public Patient(int id, String name, int age, String gender, String disease, String contact) {
        super(id, name, age, gender);
        this.disease = disease;
        this.contact = contact;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String displayInfo() {
        return "Patient ID: " + getId() + ", Name: " + getName() + ", Disease: " + disease;
    }

    @Override
    public String toString() {
        return getId() + " - " + getName() + " (" + disease + ")";
    }
}
