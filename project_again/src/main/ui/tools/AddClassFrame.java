package ui.tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Event;
import model.EventLog;
import model.Schedule;
import model.YogaClass;
import persistence.JsonWriter;

import ui.tools.GeneralScreen;

// Source: https://docs.oracle.com/javase/tutorial/uiswing/events/intro.html
// Source: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
// Source: https://www.javatpoint.com/java-swing
// Source: https://www.guru99.com/java-swing-gui.html


public class AddClassFrame extends JFrame implements ActionListener {
    Container container = getContentPane();
    JButton backButton = new JButton("Back");

    JLabel dayLabel = new JLabel("Day");
    JTextField dayTextField = new JTextField();
    JLabel timeLabel = new JLabel("Time");
    JTextField timeTextField = new JTextField();
    JLabel yogaNameLabel = new JLabel("YogaName");
    JTextField yogaNameTextField = new JTextField();
    JLabel costLabel = new JLabel("Cost");
    JTextField budgetTextField = new JTextField();
    JLabel budgetLabel = new JLabel("Enter Budget");
    JTextField costTextField = new JTextField();
    private final JTextArea textArea = new JTextArea();
    JButton confirmButton = new JButton("Confirm");
    JButton displaySchedule = new JButton("Display Schedule");
    JButton saveButton = new JButton("Save Schedule");
    JButton removeButton = new JButton("Remove Schedule");

    private static final String JSON_STORE = "./data/yogaschedule.json";
    private Schedule schedule;
    private int budget;
    private int cost;
    List<YogaClass> yogaClasses = new ArrayList<>();



    public AddClassFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        this.yogaClasses = yogaClasses;
        schedule = new Schedule("Iris's Yoga Schedule");
    }

    //set the layout of the frame
    public void setLayoutManager() {
        container.setLayout(null);
    }

    //set the location of each box
    public void setLocationAndSize() {
        backButton.setBounds(10, 10, 60, 40);

        //set day label and text box
        dayLabel.setBounds(100, 100, 100, 30);
        dayTextField.setBounds(250, 100, 150, 50);

        //set time label and text box
        timeLabel.setBounds(100, 175, 100, 30);
        timeTextField.setBounds(250, 175, 150, 50);

        //set yoga name and text box

        yogaNameLabel.setBounds(100, 250, 100, 50);
        yogaNameTextField.setBounds(250, 250, 150, 50);

        //set cost and text box

        costLabel.setBounds(100, 325, 100, 50);
        costTextField.setBounds(250, 325, 150, 50);

        //set Budget
        budgetLabel.setBounds(100, 50, 100, 30);
        budgetTextField.setBounds(250, 30, 100, 50);


        confirmButton.setBounds(500, 350, 100, 40);

        textArea.setBounds(100, 400, 400, 100);
        //display schedule button
        displaySchedule.setBounds(500,400,100,40);

        //save schedule button
        saveButton.setBounds(500,450,100,40);

        // remove class
        removeButton.setBounds(500,500,100,40);

    }

    //add components to the container
    public void addComponentsToContainer() {
        // Adding each components to the Container
        container.add(backButton);
        container.add(dayLabel);
        container.add(dayTextField);
        container.add(timeLabel);
        container.add(timeTextField);
        container.add(yogaNameLabel);
        container.add(yogaNameTextField);
        container.add(costLabel);
        container.add(costTextField);
        container.add(confirmButton);
        container.add(textArea);
        container.add(budgetTextField);
        container.add(budgetLabel);
        container.add(displaySchedule);
        container.add(saveButton);
        container.add(removeButton);
    }

    // add action to the buttons
    public void addActionEvent() {
        backButton.addActionListener(this);
        confirmButton.addActionListener(this);
        displaySchedule.addActionListener(this);
        saveButton.addActionListener(this);
        removeButton.addActionListener((this));
    }

    @Override
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void actionPerformed(ActionEvent e) {

        // BACK button logic
        if (e.getSource() == backButton) {
            GeneralScreen frame = new GeneralScreen();
            frame.setTitle("General");
            frame.setVisible(true);
            frame.setBounds(10, 10, 600, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            this.dispose();

        }
        // CONFIRM button logic
        if (e.getSource() == confirmButton) {
            String day = dayTextField.getText();
            String time = timeTextField.getText();
            String yogaName = yogaNameTextField.getText();

            try {
                cost = Integer.parseInt(costTextField.getText().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Cost value is also entered");
                return;
            }

            try {
                budget = Integer.parseInt(budgetTextField.getText().trim());

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,"Invalid budget value entered!");
                return;
            }


            // Add the new yoga class to the schedule
            addYogaClass(day, time, yogaName, cost);
        }

        // remove button logic
        if (e.getSource() == removeButton) {
            String day = dayTextField.getText();
            String time = timeTextField.getText();
            String yogaName = yogaNameTextField.getText();

            try {
                cost = Integer.parseInt(costTextField.getText().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Cost value is also entered");

            }
            // Remove the yoga class from the schedule
            removeYogaClass(day, time, yogaName, cost);
        }

        //display button logic
        if (e.getSource() == displaySchedule) {
            List<YogaClass> yogaClasses = new ArrayList<>(schedule.getYogaClasses()); // create a copy of the list
            if (yogaClasses.isEmpty()) {
                System.out.println("Your class list is empty!\n");
            } else {
                System.out.println("Here is your yoga class schedule:");
                String scheduleText = "";
                for (YogaClass yc : yogaClasses) {
                    scheduleText += yc.toString() + "\n";
                }
                JTextArea textArea = new JTextArea(scheduleText);
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(450, 300));
                JOptionPane.showMessageDialog(null,
                        scrollPane, "Yoga Class Schedule", JOptionPane.PLAIN_MESSAGE);
            }
        }

        //save the schedule
        if (e.getSource() == saveButton) {
            JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
            try {
                jsonWriter.open();
                jsonWriter.write(schedule);
                jsonWriter.close();
                JOptionPane.showMessageDialog(this, "Schedule saved to file: " + JSON_STORE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(this, "Unable to save schedule to file: "
                        + JSON_STORE);
            }
        }
    }

    int remainingBudget;
    int totalCost;
    int totalCostAfter;
    int checkBudget = 0;

    // MODIFIES: this
    //REQUIRES: budget should be higher than totalCost
    // EFFECTS: adds yogaClass to the schedule
    private void addYogaClass(String day, String time, String yogaName, int cost) {

        YogaClass yoga1 = new YogaClass(day, time, yogaName, cost);
        totalCostAfter = schedule.totalCostYogaClass() + cost;

        if (totalCostAfter <= budget) {
            schedule.addClass(yoga1);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Insufficient Budget");
        }
        budgetCalculation();
        StringBuilder sb = new StringBuilder();
        sb.append("Remaining budget for yoga: $" + remainingBudget);
        sb.append("\n" + "Total cost of classes: $" + totalCost);
        sb.append(schedule.scheduleToString());
        textArea.setText(sb.toString());
    }

    // MODIFIES: this
    // EFFECTS: calculates totalCost and remainingBudget based on the entered budget and yoga classes
    private void budgetCalculation() {
        remainingBudget = budget - schedule.totalCostYogaClass();
        yogaClasses = schedule.getYogaClasses();
        totalCost =  schedule.totalCostYogaClass();
    }

    // MODIFIES: this
    // EFFECTS: removes the yoga class
    private void removeYogaClass(String day, String time, String yogaName, int cost) {
        schedule.removeClass(day,time,yogaName,cost);
        budgetCalculation();
        StringBuilder sb = new StringBuilder();
        sb.append("Remaining budget for yoga: $" + remainingBudget);
        sb.append("\n" + "Total cost of classes: $" + totalCost);
        sb.append(schedule.toString());
        textArea.setText(sb.toString());

    }



}






