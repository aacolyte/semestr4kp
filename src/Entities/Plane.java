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
        while (mainGui.isPlaneFlyAway()
                || passengerQueue.getQueueSize() > 0
                || passengerQueue2.getQueueSize() > 0) {

            Passenger passenger;
            PassengerQueue sourceQueue;

            if (passengerQueue.getQueueSize() > 0
                    && !passengerQueue.isEscortInProgress()) {
                sourceQueue = passengerQueue;
            }
            // »наче Ч если во второй очереди есть пассажиры и она свободна
            else if (passengerQueue2.getQueueSize() > 0
                    && !passengerQueue2.isEscortInProgress()) {
                sourceQueue = passengerQueue2;
            }else{sourceQueue = passengerQueue;}


                try {
                passenger = sourceQueue.getNextPassengerToEscort();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Thread t = passenger.moveFromTo(
                    (IFromTo) sourceQueue,
                    this);
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sourceQueue.escortFinished();

            showWorking();

            passenger.moveFromTo(this, counter);
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
