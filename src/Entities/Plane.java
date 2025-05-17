package Entities;

import gui.MainGui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Plane extends AbstractEntity {
    private Counter counter;
    private JPanel panel;
    String[] images = new String[]{
            "/icons/litak1.png",
            "/icons/litak2.png",
            "/icons/litak3.png"};
    public Plane(MainGui mainGui, JLabel label, PassengerQueue passengerQueue, PassengerQueue passengerQueue2,
                 Counter counter, JPanel panel) {
        super(passengerQueue, passengerQueue2,mainGui,label);
        this.counter = counter;
        this.panel = panel;
    }


    @Override
    public void run() {
        while (this.mainGui.isPlaneFlyAway() ||
        this.passengerQueue.getQueueSize()>0) {
            Passenger passenger = null;
            try {
                passenger = this.passengerQueue.getNextPassengerToEscort();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            Thread thread = passenger.moveFromTo(this.passengerQueue,this);
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.passengerQueue.escortFinished();
            this.showWorking();
            passenger.moveFromTo(this,this.counter);
        }
    }

    public void showWorking() {

        Icon oldIcon = label.getIcon();

        SwingUtilities.invokeLater(() -> {
            try {
                label.setIcon(new ImageIcon(ImageIO.read(MainGui.class.getResource("/icons/plane_fly_away.png")).getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            panel.setBackground(Color.RED);
        });
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


            label.setIcon(oldIcon);
            panel.setBackground(Color.GREEN);

    }



}
