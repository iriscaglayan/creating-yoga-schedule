package ui.tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.Schedule;
import model.YogaClass;
import persistence.JsonReader;

public class LoadFrame extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/yogaschedule.json";
    Container container = getContentPane();
    JButton backButton = new JButton("Back");
    JButton loadButton = new JButton("Load Schedule");

    StringBuilder sb = new StringBuilder();
    private final JTextArea textArea = new JTextArea();

    private Schedule schedule;


    public LoadFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

        this.schedule = schedule;

    }


    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        // Setting location and Size of each components using setBounds() method.

        backButton.setBounds(10, 10, 60, 40);
        loadButton.setBounds(400, 400, 100, 40);
        textArea.setBounds(10, 60, 800, 100);
    }

 //addşng components to the container
    public void addComponentsToContainer() {
        container.add(backButton);
        container.add(textArea);
        container.add(loadButton);



    }

//setting the button with the user
    public void addActionEvent() {
        backButton.addActionListener(this);
        loadButton.addActionListener(this);

    }




    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
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
        if (e.getSource() == loadButton) {
            // read schedule from uploaded
            JsonReader jsonReader = new JsonReader(JSON_STORE);
            try {
                Schedule schedule = jsonReader.read();
                for (YogaClass yogaClass : schedule.getYogaClasses()) {
                    sb.append(yogaClass.toString()).append("\n");
                }
                textArea.setText(sb.toString());
                // show an image
                ImageIcon icon = new ImageIcon("./data/yoga.jpg");
                JLabel imageLabel = new JLabel(icon);
                JOptionPane.showMessageDialog(this, imageLabel, "Yoga Class Schedule",
                        JOptionPane.PLAIN_MESSAGE);

            } catch (IOException ioException) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }

        }


    }
}


