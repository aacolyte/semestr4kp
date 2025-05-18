package gui;

import Entities.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class MainGui {
    public JFrame frame;
    private   JTextField passengersDeparted;
    private Button stopButton;
    private  Button startButton;
    private   Thread threadMusic;
    private  Music music;
    private JSlider jSlider;
    private  JTextField passengersCreated;
    private  JTextField queue1;
    private  JTextField queue2;
    private JLabel personWait;
    private  JLabel airPlaneIcon1;
    private  JLabel airPlaneIcon2;
    private JLabel airPlaneIcon3;
    private  JLabel plane1;
    private JLabel plane2;
    private JLabel plane3;
    private JPanel panelPlane1;
    private JPanel panelPlane2;
    private JPanel panelPlane3;
    private Thread createPassenger;
    private Thread threadPlane1;
    private Thread threadPlane2;
    private Thread threadPlane3;
    private Creator creator;
    Plane planeObject1;
    Plane planeObject2;
    Plane planeObject3;
    PassengerQueue passengerQueue1;
    PassengerQueue passengerQueue2;
    Counter counter;
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
        frame.getContentPane().setBackground(Color.decode("#e3e1dd"));



        startButton = new Button("Start");
        startButton.setBounds(50,500,100,40);
        frame.getContentPane().add(startButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doRun();
            }
        });

        jSlider = new JSlider();
        jSlider.setBounds(1000,500,100,70);
        jSlider.setMajorTickSpacing(2);
        jSlider.setMinimum(0);
        jSlider.setMaximum(10);
        jSlider.setValue(6);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);

        jSlider.addChangeListener(e ->
                music.setVolume());

        frame.add(jSlider);




        stopButton = new Button("Stop");
        stopButton.setBounds(50,550,100,40);
        frame.getContentPane().add(stopButton);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onStop();
            }
        });

        JLabel railwayStation = new JLabel("");
        railwayStation.setBounds(50, 0, 100, 100);
        railwayStation.setIcon(new ImageIcon(ImageIO.read(MainGui.class.getResource("/icons/railwayStation.png")).getScaledInstance(railwayStation.getWidth(), railwayStation.getHeight(), Image.SCALE_SMOOTH)));
        frame.getContentPane().add(railwayStation);

        JLabel passengerCreate = new JLabel("Creating passengers ");
        passengerCreate.setBounds(25,245,200,20);
        passengerCreate.setFont(new Font("Times new Roman", Font.PLAIN, 20));
        frame.getContentPane().add(passengerCreate);

        JLabel airLineIcon = new JLabel("");
        airLineIcon.setBounds(50, 275, 100, 100);
        airLineIcon.setIcon(new ImageIcon(ImageIO.read(MainGui.class.getResource("/icons/airLineIcon.png")).getScaledInstance(airLineIcon.getWidth(), airLineIcon.getHeight(), Image.SCALE_SMOOTH)));
        frame.getContentPane().add(airLineIcon);

        passengersCreated = new JTextField("30");
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

        queue1 = new JTextField();
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

        queue2 = new JTextField();
        queue2.setBounds(360,535,100,20);
        queue2.setEditable(false);
        queue2.setFocusable(false);
        frame.getContentPane().add(queue2);



        plane1 = new JLabel("Plane 1");
        plane1.setBounds(720,50,200,20);
        plane1.setFont(new Font("Times new Roman", Font.PLAIN, 20));
        frame.getContentPane().add(plane1);

        airPlaneIcon1 = new JLabel("");
        airPlaneIcon1.setBounds(700, 75, 100, 100);
        airPlaneIcon1.setIcon(new ImageIcon(ImageIO.read(MainGui.class.getResource("/icons/litak1.png")).getScaledInstance(airLineIcon.getWidth(), airLineIcon.getHeight(), Image.SCALE_SMOOTH)));
        frame.getContentPane().add(airPlaneIcon1);

        panelPlane1 = new JPanel();
        panelPlane1.setBounds(700,180,100,7);
        panelPlane1.setBackground(Color.GREEN);
        frame.getContentPane().add(panelPlane1);

        plane2 = new JLabel("Plane 2");
        plane2.setBounds(720,245,200,20);
        plane2.setFont(new Font("Times new Roman", Font.PLAIN, 20));
        frame.getContentPane().add(plane2);

        airPlaneIcon2 = new JLabel("");
        airPlaneIcon2.setBounds(700, 270, 100, 100);
        airPlaneIcon2.setIcon(new ImageIcon(ImageIO.read(MainGui.class.getResource("/icons/litak2.png")).getScaledInstance(airLineIcon.getWidth(), airLineIcon.getHeight(), Image.SCALE_SMOOTH)));
        frame.getContentPane().add(airPlaneIcon2);

        panelPlane2 = new JPanel();
        panelPlane2.setBounds(700,375,100,7);
        panelPlane2.setBackground(Color.GREEN);
        frame.getContentPane().add(panelPlane2);

        plane3 = new JLabel("Plane 3");
        plane3.setBounds(720,445,200,20);
        plane3.setFont(new Font("Times new Roman", Font.PLAIN, 20));
        frame.getContentPane().add(plane3);

        airPlaneIcon3 = new JLabel("");
        airPlaneIcon3.setBounds(700, 470, 100, 100);
        airPlaneIcon3.setIcon(new ImageIcon(ImageIO.read(MainGui.class.getResource("/icons/litak3.png")).getScaledInstance(airLineIcon.getWidth(), airLineIcon.getHeight(), Image.SCALE_SMOOTH)));
        frame.getContentPane().add(airPlaneIcon3);

        panelPlane3 = new JPanel();
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

        passengersDeparted = new JTextField("0");
        passengersDeparted.setBounds(1000,375,100,20);
        passengersDeparted.setEditable(false);
        passengersDeparted.setFocusable(false);
        frame.getContentPane().add(passengersDeparted);

        personWait = new JLabel("");
        personWait.setBounds(150, 374, 46, 14);
        frame.getContentPane().add(personWait);

    }

    private void doRun(){
        threadMusic = new Thread(){
            @Override
            public void run(){
                music = new Music(jSlider);
                music.playMusic();
                music.musicLoop();
                new Thread(()->{
                   while(true){
                       music.setVolume();
                       try {
                           Thread.sleep(500);
                       } catch (InterruptedException e) {
                           throw new RuntimeException(e);
                       }
                   }
                }).start();;
            }
        };
        passengersCreated.setEnabled(false);
        startButton.setEnabled(false);
        if(counter==null) {
            counter = new Counter(passengersDeparted);
        }
        if(passengerQueue1 == null && passengerQueue2 == null) {
            passengerQueue1 = new PassengerQueue(this, queue1);
            passengerQueue2 = new PassengerQueue(this, queue2);
        }

        creator = new Creator(this,personWait,passengerQueue1,passengerQueue2,passengersCreated);
        planeObject1 = new Plane(this,airPlaneIcon1,passengerQueue1,passengerQueue2,counter,panelPlane1);
        planeObject2 = new Plane(this,airPlaneIcon2,passengerQueue1,passengerQueue2,counter,panelPlane2);
        planeObject3 = new Plane(this,airPlaneIcon3,passengerQueue1,passengerQueue2,counter,panelPlane3);


        (createPassenger = new Thread(creator)).start();
        (threadPlane1 = new Thread(planeObject1)).start();
        (threadPlane2 = new Thread(planeObject2)).start();
        (threadPlane3 = new Thread(planeObject3)).start();
        threadMusic.start();



    }
    private void onStop(){
        music.stopMusic();
        creator.plsStop();
        planeObject1.plsStop();
        planeObject2.plsStop();
        planeObject3.plsStop();
        endOfWork();


    }
    private void endOfWork(){
        new Thread(){
            @Override
            public void run() {
                try {
                    createPassenger.join();
                    threadPlane1.join();
                    threadPlane2.join();
                    threadPlane3.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                SwingUtilities.invokeLater(() -> {
                    startButton.setEnabled(true);
                    passengersCreated.setEnabled(true);
                });
            }

        }.start();
    }

    public boolean isPlaneFlyAway(){
        return !planeObject1.isStopped;
    }

}
