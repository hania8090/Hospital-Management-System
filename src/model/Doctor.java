package model;

public class Doctor extends Person {
    private String specialization;
    private String availableTime;

    public Doctor(int id, String name, int age, String gender, String specialization, String availableTime) {
        super(id, name, age, gender);
        this.specialization = specialization;
        this.availableTime = availableTime;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }

    @Override
    public String displayInfo() {
        return "Doctor ID: " + getId() + ", Name: " + getName() + ", Specialization: " + specialization;
    }

    @Override
    public String toString() {
        return getId() + " - " + getName() + " (" + specialization + ")";
    }
}
