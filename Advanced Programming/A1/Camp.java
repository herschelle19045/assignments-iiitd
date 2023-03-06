package Assignment1;

import java.util.ArrayList;

public class Camp {
    private final ArrayList<Patient> patients;
    private final ArrayList<Hospital> hospitals;
    private int patientsInCamp;

    public Camp(ArrayList<Patient> patients) {
        this.patients = patients;
        hospitals = new ArrayList<>();
        patientsInCamp = patients.size();
    }

    public int getPatientsInCamp() {
        return patientsInCamp;
    }

    public void decrementPatientsInCamp() {
        patientsInCamp--;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public int openHospitalsCount() {
        int count=0;
        for(Hospital h : hospitals)
            if (h.isAccepting())
                count++;
        return count;
    }

    public void removeAdmittedPatients() {
        int count=0;
        for (Patient p : patients) {
            if (p.getAdmitted()) count++;
        }

        if(count == 0)
            System.out.println("No admitted patients available to remove");
        else {
            System.out.println("Account id removed of admitted patients");
            for (int i = 0; i < patients.size(); i++) {
                Patient p = patients.get(i);
                if (p.getAdmitted()) {
                    System.out.println(p.getId());
                    patients.remove(p);
                    i--;
                }
            }
        }
    }

    public void addHospital(Hospital h) {
        hospitals.add(h);
    }

    public void removeClosedHospitals() {
        int count=0;
        for (Hospital h : hospitals) {
            if (!h.isAccepting()) count++;
        }

        if(count == 0)
            System.out.println("No closed Institute available to remove");
        else {
            System.out.println("Accounts removed of Institute whose admission is closed");
            for (int i = 0; i < hospitals.size(); i++) {
                Hospital h = hospitals.get(i);
                if (!h.isAccepting()) {
                    System.out.println(h.getName());
                    hospitals.remove(h);
                    i--;
                }
            }
        }
    }

    public void displayAllPatients() {
        for(Patient p : patients) System.out.println(p.getId() + " " + p.getName());
    }
}
