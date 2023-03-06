package Assignment1;

public class Patient {
    private final int id;
    private final String name;
    private int age;
    private final int oxygenLevel;
    private final double temperature;
    private int recoveryTime;
    private boolean isAdmitted;         // True if admitted
    private String hospitalAssigned;
    private static int count = 0;

    public Patient(String name, int age, int oxygenLevel, double temperature) {
        id = ++count;
        this.name = name;
        this.age = age;
        this.oxygenLevel = oxygenLevel;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOxygenLevel() {
        return oxygenLevel;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getRecoveryTime() {
        return recoveryTime;
    }

    public boolean getAdmitted() {
        return isAdmitted;
    }

    public void setRecoveryTime(int recoveryTime) {
        this.recoveryTime = recoveryTime;
    }

    public void setAdmitted(boolean admitted) {
        this.isAdmitted = admitted;
    }

    public void setHospitalAssigned(String hospitalAssigned) {
        this.hospitalAssigned = hospitalAssigned;
    }

    public void display() {
        System.out.println(name);
        System.out.println("Temperature is " + temperature);
        System.out.println("Oxygen level is " + oxygenLevel);
        System.out.println("Admission Status - " + (isAdmitted ? "Admitted" : "None"));
        if(isAdmitted)
            System.out.println("Admitting Institute - " + hospitalAssigned);
    }
}
