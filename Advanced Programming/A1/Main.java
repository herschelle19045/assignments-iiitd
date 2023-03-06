package Assignment1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final ArrayList<Patient> patients = new ArrayList<>();
    private static final ArrayList<Hospital> hospitals = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        System.out.print("Enter number of patients: ");
        int q = scanner.nextInt();

//        System.out.println("Enter record of " + q + " patients:");
        for (int i = 0; i < q; i++) {
            String name = scanner.next();
            double temperature = scanner.nextDouble();
            int oxygenLevel = scanner.nextInt();
            int age = scanner.nextInt();

            Patient p = new Patient(name, age, oxygenLevel, temperature);
            patients.add(p);
        }

        @SuppressWarnings("unchecked")
        Camp camp = new Camp((ArrayList<Patient>) patients.clone());

        while (camp.getPatientsInCamp() != 0) {

//            System.out.print("Enter query: ");
            int choice = scanner.nextByte();
            // Add hospital
            if (choice == 1) {
//                System.out.print("Enter name of the institute - ");
                String name = scanner.next();

                System.out.print("Temperature Criteria - ");
                double temperature = scanner.nextDouble();

                System.out.print("Oxygen Levels – ");
                int oxygenLevel = scanner.nextInt();

                System.out.print("Number of Available beds – ");
                int bedCount = scanner.nextInt();

                Hospital h = new Hospital(name, temperature, oxygenLevel, bedCount);
                hospitals.add(h);
                camp.addHospital(h);
                h.display();
                System.out.println("Admission status - " + (h.getVacantBeds() == 0 ? "CLOSED" : "OPEN"));
                System.out.println("Number of Available beds - " + bedCount);

                // Admit patients to this hospital

                for (Patient p : camp.getPatients()) {
                    if (!p.getAdmitted() && p.getOxygenLevel() >= oxygenLevel && h.getVacantBeds() > 0) {
                        h.addPatient(p);
                        p.setAdmitted(true);
                        p.setHospitalAssigned(h.getName());
                        camp.decrementPatientsInCamp();
                    }
                }

                for (Patient p : camp.getPatients()) {
                    if (!p.getAdmitted() && p.getTemperature() <= temperature && h.getVacantBeds() > 0) {
                        h.addPatient(p);
                        p.setAdmitted(true);
                        p.setHospitalAssigned(h.getName());
                        camp.decrementPatientsInCamp();
                    }
                }

                // Prompt for recovery dates

                for (Patient p : h.getPatients()) {
                    System.out.print("Recovery days for admitted patient ID " + p.getId() + " - ");
                    int d = scanner.nextInt();
                    p.setRecoveryTime(d);
                }
            }

            if (choice == 2) {
                camp.removeAdmittedPatients();
            }

            if (choice == 3) {
                camp.removeClosedHospitals();
            }

            if (choice == 4) {
                System.out.println(camp.getPatientsInCamp() + " patients");
            }

            if (choice == 5) {
                System.out.println(camp.openHospitalsCount() + " institutes are admitting patients currently");
            }

            if (choice == 6) {
                String name = scanner.next();
                Hospital h = getHospitalByName(name);
                assert h != null;
                h.display();
                System.out.println("Number of available beds - " + h.getVacantBeds());
                System.out.println("Admission Status - " + (h.getVacantBeds() > 0 ? "OPEN" : "CLOSED"));
            }

            if (choice == 7) {
                int id = scanner.nextByte();
                Patient p = getPatientById(id);
                assert p != null;
                p.display();
            }

            if (choice == 8) {
                camp.displayAllPatients();
            }

            if (choice == 9) {
                String name = scanner.next();
                Hospital h = getHospitalByName(name);
                assert h != null;
                displayAllPatientsInHospital(h);
            }
        }
    }

    private static Patient getPatientById(int id) {
        for(Patient p : patients) {
            if(p.getId() == id)
                return p;
        }
        return null;
    }

    private static Hospital getHospitalByName(String name) {
        for(Hospital h : hospitals) {
            if(h.getName().equals(name))
                return h;
        }
        return null;
    }

    private static void displayAllPatientsInHospital(Hospital h) {
        if(h.getPatients().size() == 0)
            System.out.println("No patients in this hospital");

        for(Patient p : h.getPatients()) {
            System.out.println(p.getName() + ", recovery time is " + p.getRecoveryTime() + " days");
        }
    }
}
