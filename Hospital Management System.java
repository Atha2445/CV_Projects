import java.util.*;

// Base class for Person
abstract class Person {
    protected String name;
    protected int age;
    protected String gender;

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Gender: " + gender;
    }
}

// Doctor class inheriting Person
class Doctor extends Person {
    private String specialization;

    public Doctor(String name, int age, String gender, String specialization) {
        super(name, age, gender);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return super.toString() + ", Specialization: " + specialization;
    }
}

// Patient class inheriting Person
class Patient extends Person {
    private String illness;

    public Patient(String name, int age, String gender, String illness) {
        super(name, age, gender);
        this.illness = illness;
    }

    public String getIllness() {
        return illness;
    }

    @Override
    public String toString() {
        return super.toString() + ", Illness: " + illness;
    }
}

// Appointment class
class Appointment {
    private Doctor doctor;
    private Patient patient;
    private String date;

    public Appointment(Doctor doctor, Patient patient, String date) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Appointment: Doctor=" + doctor.name + ", Patient=" + patient.name + ", Date=" + date;
    }
}

// Hospital Management System
public class HospitalManagementSystem {

    private static final List<Doctor> doctors = new ArrayList<>();
    private static final List<Patient> patients = new ArrayList<>();
    private static final List<Appointment> appointments = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Add Doctor");
            System.out.println("2. Add Patient");
            System.out.println("3. Book Appointment");
            System.out.println("4. View Doctors");
            System.out.println("5. View Patients");
            System.out.println("6. View Appointments");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = getValidIntegerInput();
            switch (choice) {
                case 1 -> addDoctor();
                case 2 -> addPatient();
                case 3 -> bookAppointment();
                case 4 -> viewDoctors();
                case 5 -> viewPatients();
                case 6 -> viewAppointments();
                case 7 -> {
                    System.out.println("Exiting system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addDoctor() {
        System.out.println("\n--- Add Doctor ---");
        System.out.print("Enter Doctor's Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = getValidIntegerInput();
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Specialization: ");
        String specialization = scanner.nextLine();

        doctors.add(new Doctor(name, age, gender, specialization));
        System.out.println("Doctor added successfully!\n");
    }

    private static void addPatient() {
        System.out.println("\n--- Add Patient ---");
        System.out.print("Enter Patient's Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = getValidIntegerInput();
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Illness: ");
        String illness = scanner.nextLine();

        patients.add(new Patient(name, age, gender, illness));
        System.out.println("Patient added successfully!\n");
    }

    private static void bookAppointment() {
        System.out.println("\n--- Book Appointment ---");
        if (doctors.isEmpty() || patients.isEmpty()) {
            System.out.println("Doctors or Patients list is empty. Please add them first.");
            return;
        }

        System.out.println("Available Doctors:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i));
        }
        System.out.print("Choose Doctor (Enter number): ");
        int doctorIndex = getValidIndexInput(doctors.size());

        System.out.println("Available Patients:");
        for (int i = 0; i < patients.size(); i++) {
            System.out.println((i + 1) + ". " + patients.get(i));
        }
        System.out.print("Choose Patient (Enter number): ");
        int patientIndex = getValidIndexInput(patients.size());

        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        appointments.add(new Appointment(doctors.get(doctorIndex), patients.get(patientIndex), date));
        System.out.println("Appointment booked successfully!\n");
    }

    private static void viewDoctors() {
        System.out.println("\n--- List of Doctors ---");
        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
        } else {
            doctors.forEach(System.out::println);
        }
    }

    private static void viewPatients() {
        System.out.println("\n--- List of Patients ---");
        if (patients.isEmpty()) {
            System.out.println("No patients available.");
        } else {
            patients.forEach(System.out::println);
        }
    }

    private static void viewAppointments() {
        System.out.println("\n--- List of Appointments ---");
        if (appointments.isEmpty()) {
            System.out.println("No appointments available.");
        } else {
            appointments.forEach(System.out::println);
        }
    }

    private static int getValidIntegerInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a valid number: ");
            }
        }
    }

    private static int getValidIndexInput(int maxSize) {
        while (true) {
            int index = getValidIntegerInput() - 1;
            if (index >= 0 && index < maxSize) {
                return index;
            } else {
                System.out.print("Invalid choice! Please select a valid number: ");
            }
        }
    }
}
