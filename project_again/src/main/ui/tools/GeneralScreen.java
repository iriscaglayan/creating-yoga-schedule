package ui.tools;

import model.Event;
import model.EventLog;
import model.Schedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GeneralScreen extends JFrame implements ActionListener {


    Container container = getContentPane();
    private JComboBox<String> printCombo;
    private JDesktopPane desktop;
    //add the buttons

    JButton addClassButton = new JButton("Add or Remove Class");
    JButton removeClassBUtton = new JButton("Remove Class");
    JButton budgetButton = new JButton("Budget");
    JButton displayScheduleButton = new JButton("Display Schedule");
    JButton saveScheduleButton = new JButton("Save Schedule");
    JButton loadScheduleButton = new JButton("Load Schedule");
    JButton quitButton = new JButton("Quit");



    // all of these constructors (as can be seen in most files) need to be
    // set to public so they're accessible by importing them to other files

    public GeneralScreen() {
        // Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }


    public void setLayoutManager() {
        container.setLayout(null);
    }

    // Setting location and Size of each components using setBounds() method.
    public void setLocationAndSize() {
        addClassButton.setBounds(100, 350, 200, 40);
        loadScheduleButton.setBounds(350, 350, 100, 40);
        quitButton.setBounds(250,450,100,40);

    }

    public void addComponentsToContainer() {
        // Adding each components to the Container

        container.add(addClassButton);
        container.add(loadScheduleButton);
        container.add(quitButton);
    }

    //setting the actions of each button
    public void addActionEvent() {
        addClassButton.addActionListener(this);
        loadScheduleButton.addActionListener(this);
        quitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //addClassbutton logic
        if (e.getSource() == addClassButton) {
            AddClassFrame frame = new AddClassFrame();
            frame.setTitle("Add Class");
            frame.setVisible(true);
            frame.setBounds(10, 10, 600, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        }


        // logic of visit load schedule button
        if (e.getSource() == loadScheduleButton) {
            LoadFrame frame = new LoadFrame();
            frame.setTitle("LoadFrame");
            frame.setVisible(true);
            frame.setBounds(10, 10, 600, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        }
        //quit button logic
        if (e.getSource() == quitButton) {
            System.out.println("Thank you for creating a yoga class schedule with my app");
            for (Event event : EventLog.getInstance()) {
                System.out.println(event);
            }
        }


    }



}










