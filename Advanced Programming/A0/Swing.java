package Assignment0;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Swing extends JFrame implements ActionListener {

    static JFrame frame;
    JTable table;
    JTable towerTable;

    JTextField date, month, year;
    JCheckBox aCheckBox, bCheckBox, cCheckBox, dCheckBox;
    JButton submitButton, resetButton;

    Patient[] res;

    int inputDay, inputMonth, inputYear;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    Swing() {
        frame = new JFrame();
        inputSection();
        frame.setSize(475, 600);
        frame.setLayout(new FlowLayout());
        frame.setTitle("COVID-19 Cases");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void inputSection() {
        JLabel dayText = new JLabel();
        dayText.setText("DD");
        date = new JTextField(2);
        frame.add(dayText);
        frame.add(date);

        JLabel monthText = new JLabel();
        monthText.setText("MM");
        month = new JTextField(2);
        frame.add(monthText);
        frame.add(month);

        JLabel yearText = new JLabel();
        yearText.setText("YYYY");
        year = new JTextField(4);
        frame.add(yearText);
        frame.add(year);

        JLabel aText = new JLabel();
        aText.setText("A");
        aCheckBox = new JCheckBox();
        frame.add(aText);
        frame.add(aCheckBox);

        JLabel bText = new JLabel();
        bText.setText("B");
        bCheckBox = new JCheckBox();
        frame.add(bText);
        frame.add(bCheckBox);

        JLabel cText = new JLabel();
        cText.setText("C");
        cCheckBox = new JCheckBox();
        frame.add(cText);
        frame.add(cCheckBox);

        JLabel dText = new JLabel();
        dText.setText("D");
        dCheckBox = new JCheckBox();
        frame.add(dText);
        frame.add(dCheckBox);

        submitButton = new JButton();
        submitButton.setText("Submit");
        frame.add(submitButton);
        submitButton.addActionListener(this);

        resetButton = new JButton();
        resetButton.setText("Reset");
        frame.add(resetButton);
        resetButton.setVisible(false);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Swing();
            }
        });
    }

    void tableSection() throws ParseException {
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        String[][] patientData = new String[20][6];
        int aActive=0, aRecovered=0, bActive=0, bRecovered=0, cActive=0, cRecovered=0, dActive=0, dRecovered=0;
        for (int i = 0; i < res.length; i++) {
            String status = "Active";
            if (dateFormat.parse(inputDay + "-" + inputMonth + "-" + inputYear).compareTo(res[i].recoveryDate) >= 0) {
                status = "Recovered";
                if(res[i].tower == 'A') aRecovered++;
                if(res[i].tower == 'B') bRecovered++;
                if(res[i].tower == 'C') cRecovered++;
                if(res[i].tower == 'D') dRecovered++;
            }
            else {
                if(res[i].tower == 'A') aActive++;
                if(res[i].tower == 'B') bActive++;
                if(res[i].tower == 'C') cActive++;
                if(res[i].tower == 'D') dActive++;
            }
            patientData[i] = new String[]{res[i].name, String.valueOf(res[i].age), String.valueOf(res[i].tower), status, date.format(res[i].date), date.format(res[i].recoveryDate)};
        }
        String[] columnNames = {"Name", "Age", "Tower", "Status", "Reporting Date", "Recovery Date"};
        table = new JTable(patientData, columnNames);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(2).setPreferredWidth(30);
        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);

        String[][] towerData = new String[4][3];
        towerData[0] = new String[]{"A", (aActive == 0 && aRecovered == 0) ? "-" : String.valueOf(aActive), (aActive == 0 && aRecovered == 0) ? "-" : String.valueOf(aRecovered)};
        towerData[1] = new String[]{"B", (bActive == 0 && bRecovered == 0) ? "-" : String.valueOf(bActive), (bActive == 0 && bRecovered == 0) ? "-" : String.valueOf(bRecovered)};
        towerData[2] = new String[]{"C", (cActive == 0 && cRecovered == 0) ? "-" : String.valueOf(cActive), (cActive == 0 && cRecovered == 0) ? "-" : String.valueOf(cRecovered)};
        towerData[3] = new String[]{"D", (dActive == 0 && dRecovered == 0) ? "-" : String.valueOf(dActive), (dActive == 0 && dRecovered == 0) ? "-" : String.valueOf(dRecovered)};

        String[] columnTowers = {"", "Active", "Recovered"};
        towerTable = new JTable(towerData, columnTowers);
        JScrollPane sp2 = new JScrollPane(towerTable);
        frame.add(sp2);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        submitButton.setVisible(false);
        resetButton.setVisible(true);

        String dayString = this.date.getText().strip(),
                monthString = this.month.getText().strip(),
                yearString = this.year.getText().strip();

        if(!validateString(dayString, monthString, yearString)) {
            JLabel wrongDate = new JLabel();
            wrongDate.setText("Enter a valid date between 1-4-2020 to 31-8-2020 in dd-mm-yyyy");
            frame.add(wrongDate);
        }
        else {
            inputDay = Integer.parseInt(dayString);
            inputMonth = Integer.parseInt(monthString);
            inputYear = Integer.parseInt(yearString);

            if (!isValidDate(inputDay, inputMonth, inputYear)) {
                JLabel wrongDate = new JLabel();
                wrongDate.setText("Please enter a valid date between April-1 to August-31 in dd-mm-yyyy");
                frame.add(wrongDate);
            } else {
                boolean aCheckBox = this.aCheckBox.isSelected(),
                        bCheckBox = this.bCheckBox.isSelected(),
                        cCheckBox = this.cCheckBox.isSelected(),
                        dCheckBox = this.dCheckBox.isSelected();

                Patient[] patientsByDate = new Patient[0];
                try {
                    patientsByDate = Main.patientsBeforeDate(Main.patients, dateFormat.parse(inputDay+"-"+inputMonth+"-"+inputYear));
                } catch (ParseException parseException) {
                    System.out.println("Parse Exception");
                }
                ArrayList<Patient> patientsByTower = new ArrayList<>();

                if (aCheckBox) {
                    Patient[] p = Main.filterByTower(patientsByDate, 'A');
                    patientsByTower.addAll(Arrays.asList(p));
                }
                if (bCheckBox) {
                    Patient[] p = Main.filterByTower(patientsByDate, 'B');
                    patientsByTower.addAll(Arrays.asList(p));
                }
                if (cCheckBox) {
                    Patient[] p = Main.filterByTower(patientsByDate, 'C');
                    patientsByTower.addAll(Arrays.asList(p));
                }
                if (dCheckBox) {
                    Patient[] p = Main.filterByTower(patientsByDate, 'D');
                    patientsByTower.addAll(Arrays.asList(p));
                }

                res = patientsByTower.toArray(Patient[]::new);

                try {
                    tableSection();
                } catch (ParseException parseException) {
                    System.out.println("Parse Exception");
                }
            }
        }
    }

    boolean validateString(String date, String month, String year) {
        if(date.length() != 2 || month.length() == 0 || month.length() > 2 || !year.equals("2020"))
            return false;
        for (int i = 0; i < date.length(); i++) {
            if(!Character.isDigit(date.charAt(i)))
                return false;
        }
        for(char c : month.toCharArray()) {
            if(!Character.isDigit(c))
                return false;
        }
        return true;
    }

    boolean isValidDate(int date, int month, int year) {
        if (date < 1 || month < 1 || month > 12 || year != 2020)
            return false;
        if (month == 4) { // April-30
            return date <= 30;
        } else if (month == 5) { // May-31
            return date <= 31;
        } else if (month == 6) { // June-30
            return date <= 30;
        } else if (month == 7) { // July-31
            return date <= 31;
        } else if (month == 8) { // August-31
            return date <= 31;
        }
        return false;
    }

}
