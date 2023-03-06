package Assignment0;

import java.util.Calendar;
import java.util.Date;

public class Patient {
    String name;
    int age;
    char tower;
    Date date;
    Date recoveryDate;

    public Patient(String name, int age, char tower, Date date) {
        this.name = name;
        this.age = age;
        this.tower = tower;
        this.date = date;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 22);
        recoveryDate = c.getTime();
    }
}
