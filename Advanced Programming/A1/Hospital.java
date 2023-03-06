package Assignment1;

import java.util.ArrayList;

public class Hospital {
    private final ArrayList<Patient> patients;
    private final String name;
    private final double temperatureCriteria;
    private final int oxygenCriteria;
    private final int bedCount;
    private int vacantBeds;
    private boolean isAccepting;         // True if accepting/open

    public Hospital(String name, double temperatureCriteria, int oxygenCriteria, int bedCount) {
        patients = new ArrayList<>();
        this.name = name;
        this.temperatureCriteria = temperatureCriteria;
        this.oxygenCriteria = oxygenCriteria;
        this.bedCount = bedCount;
        vacantBeds = bedCount;
        isAccepting = vacantBeds != 0;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public String getName() {
        return name;
    }

    public int getVacantBeds() {
        return vacantBeds;
    }

    public boolean isAccepting() {
        return isAccepting;
    }

    public void addPatient(Patient p) {
        if(isAccepting) patients.add(p);
        vacantBeds = bedCount - patients.size();
        if(vacantBeds == 0) isAccepting = false;
    }

    public void display() {
        System.out.println(name);
        System.out.println("Temperature should be <= " + temperatureCriteria);
        System.out.println("Oxygen should be >= " + oxygenCriteria);
    }

}
