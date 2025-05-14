package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainGui {
    public JFrame frame;
    JTextField passengersDeparted;
    Button stopButton;
    Button startButton;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainGui mainGui = null;
                try {
                    mainGui = new MainGui();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                mainGui.frame.setVisible(true);
            }
        });
    }
    public MainGui() throws IOException {
        initialize();
    }
    private void initialize() throws IOException {
        frame = new JFrame();
        frame.setBounds(100, 100, 1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        startButton = new Button("Start");
        startButton.setBounds(50,500,100,40);
        frame.getContentPane().add(startButton);

        stopButton = new Button("Stop");
        stopButton.setBounds(50,550,100,40);
        frame.getContentPane().add(stopButton);

        JLabel passengerCreate = new JLabel("Creating passengers ");
        passengerCreate.setBounds(25,245,200,20);
        passengerCreate.setFont(new Font("Times new Roman", Font.PLAIN, 20));
        frame.getContentPane().add(passengerCreate);

        JLabel airLineIcon = new JLabel("");
        airLineIcon.setBounds(50, 275, 100, 100);
        airLineIcon.setIcon(new ImageIcon(ImageIO.read(MainGui.class.getResource("/icons/airLineIcon.png")).getScaledInstance(airLineIcon.getWidth(), airLineIcon.getHeight(), Image.SCALE_SMOOTH)));
        frame.getContentPane().add(airLineIcon);

        JTextField passengersCreated = new JTextField();
        passengersCreated.setBounds(50,380,100,20);
        passengersCreated.setEditable(false);
        passengersCreated.setFocusable(false);
        frame.getContentPane().add(passengersCreated);





        JLabel ticketsQueue1 = new JLabel("Tickets Queue 1");
        ticketsQueue1.setBounds(345,100,200,20);
        ticketsQueue1.setFont(new Font("Times new Roman", Font.PLAIN, 20));
        frame.getContentPane().add(ticketsQueue1);

        JLabel ticketsIcon1 = new JLabel("");
        ticketsIcon1.setBounds(360, 130, 100, 100);
        ticketsIcon1.setIcon(new ImageIcon(ImageIO.read(MainGui.class.getResource("/icons/tickets.png")).getScaledInstance(airLineIcon.getWidth(), airLineIcon.getHeight(), Image.SCALE_SMOOTH)));
        frame.getContentPane().add(ticketsIcon1);

        JTextField queue1 = new JTextField();
        queue1.setBounds(360,235,100,20);
        queue1.setEditable(false);
        queue1.setFocusable(false);
        frame.getContentPane().add(queue1);




        JLabel ticketsQueue2 = new JLabel("Tickets Queue 2");
        ticketsQueue2.setBounds(345,400,200,20);
        ticketsQueue2.setFont(new Font("Times new Roman", Font.PLAIN, 20));
        frame.getContentPane().add(ticketsQueue2);

        JLabel ticketsIcon2 = new JLabel("");
        ticketsIcon2.setBounds(360, 430, 100, 100);
        ticketsIcon2.setIcon(new ImageIcon(ImageIO.read(MainGui.class.getResource("/icons/tickets.png")).getScaledInstance(airLineIcon.getWidth(), airLineIcon.getHeight(), Image.SCALE_SMOOTH)));
        frame.getContentPane().add(ticketsIcon2);

        JTextField queue2 = new JTextField();
        queue2.setBounds(360,535,100,20);
        queue2.setEditable(false);
        queue2.setFocusable(false);
        frame.getContentPane().add(queue2);



        JLabel plane1 = new JLabel("Plane 1");
        plane1.setBounds(720,50,200,20);
        plane1.setFont(new Font("Times new Roman", Font.PLAIN, 20));
        frame.getContentPane().add(plane1);

        JLabel airPlaneIcon1 = new JLabel("");
        airPlaneIcon1.setBounds(700, 75, 100, 100);
        airPlaneIcon1.setIcon(new ImageIcon(ImageIO.read(MainGui.class.getResource("/icons/litak1.png")).getScaledInstance(airLineIcon.getWidth(), airLineIcon.getHeight(), Image.SCALE_SMOOTH)));
        frame.getContentPane().add(airPlaneIcon1);

        JPanel panelPlane1 = new JPanel();
        panelPlane1.setBounds(700,180,100,7);
        panelPlane1.setBackground(Color.GREEN);
        frame.getContentPane().add(panelPlane1);

        JLabel plane2 = new JLabel("Plane 2");
        plane2.setBounds(720,245,200,20);
        plane2.setFont(new Font("Times new Roman", Font.PLAIN, 20));
        frame.getContentPane().add(plane2);

        JLabel airPlaneIcon2 = new JLabel("");
        airPlaneIcon2.setBounds(700, 270, 100, 100);
        airPlaneIcon2.setIcon(new ImageIcon(ImageIO.read(MainGui.class.getResource("/icons/litak2.png")).getScaledInstance(airLineIcon.getWidth(), airLineIcon.getHeight(), Image.SCALE_SMOOTH)));
        frame.getContentPane().add(airPlaneIcon2);

        JPanel panelPlane2 = new JPanel();
        panelPlane2.setBounds(700,375,100,7);
        panelPlane2.setBackground(Color.GREEN);
        frame.getContentPane().add(panelPlane2);

        JLabel plane3 = new JLabel("Plane 3");
        plane3.setBounds(720,445,200,20);
        plane3.setFont(new Font("Times new Roman", Font.PLAIN, 20));
        frame.getContentPane().add(plane3);

        JLabel airPlaneIcon3 = new JLabel("");
        airPlaneIcon3.setBounds(700, 470, 100, 100);
        airPlaneIcon3.setIcon(new ImageIcon(ImageIO.read(MainGui.class.getResource("/icons/litak3.png")).getScaledInstance(airLineIcon.getWidth(), airLineIcon.getHeight(), Image.SCALE_SMOOTH)));
        frame.getContentPane().add(airPlaneIcon3);

        JPanel panelPlane3 = new JPanel();
        panelPlane3.setBounds(700,575,100,7);
        panelPlane3.setBackground(Color.GREEN);
        frame.getContentPane().add(panelPlane3);

        JLabel passengerDeparted = new JLabel("Passengers Departed");
        passengerDeparted.setBounds(970,240,200,20);
        passengerDeparted.setFont(new Font("Times new Roman", Font.PLAIN, 20));
        frame.getContentPane().add(passengerDeparted);

        JLabel cntOfDepartedPassengers = new JLabel("");
        cntOfDepartedPassengers.setBounds(1000, 270, 100, 100);
        cntOfDepartedPassengers.setIcon(new ImageIcon(ImageIO.read(MainGui.class.getResource("/icons/gawaiMan.png")).getScaledInstance(airLineIcon.getWidth(), airLineIcon.getHeight(), Image.SCALE_SMOOTH)));
        frame.getContentPane().add(cntOfDepartedPassengers);

        passengersDeparted = new JTextField();
        passengersDeparted.setBounds(1000,375,100,20);
        passengersDeparted.setEditable(false);
        passengersDeparted.setFocusable(false);
        frame.getContentPane().add(passengersDeparted);
    }

    private void doRun(){
        passengersDeparted.setText("0");
        startButton.setEnabled(false);
        //тут начать циклы из isPlaneFlyAway;

    }

    public boolean isPlaneFlyAway(){
        return !(!this.thread1.isAlive() && !this.thread2.isAlive());
    }

}
