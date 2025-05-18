package Entities;

import gui.MainGui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Plane extends AbstractEntity {
    private Counter counter;
    private JPanel panel;
    private boolean isStopped = false;
    public Plane(MainGui mainGui, JLabel label, PassengerQueue passengerQueue, PassengerQueue passengerQueue2,
                 Counter counter, JPanel panel) {
        super(passengerQueue, passengerQueue2,mainGui,label);
        this.counter = counter;
        this.panel = panel;
    }


    @Override
    public void run() {
        while ((mainGui.isPlaneFlyAway()
                || passengerQueue.getQueueSize() > 0
                || passengerQueue2.getQueueSize() > 0)
                && !isStopped) {

            Passenger passenger = null;
            PassengerQueue sourceQueue = null;

            while (passenger == null) {
                int size1 = passengerQueue.getQueueSize();
                int size2 = passengerQueue2.getQueueSize();
                boolean escort1 = passengerQueue.isEscortInProgress();
                boolean escort2 = passengerQueue2.isEscortInProgress();

                if (size1 > 0 && !escort1 && (size1 >= size2 || escort2)) {
                    sourceQueue = passengerQueue;
                } else if (size2 > 0 && !escort2) {
                    sourceQueue = passengerQueue2;
                } else {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        // throw new RuntimeException(e);
                    }
                    continue;
                }

                passenger = sourceQueue.tryGetNextPassengerToEscort();
                if (passenger == null) {
                    if (sourceQueue == passengerQueue && size2 > 0 && !escort2) {
                        sourceQueue = passengerQueue2;
                        passenger = sourceQueue.tryGetNextPassengerToEscort();
                    } else if (sourceQueue == passengerQueue2 && size1 > 0 && !escort1) {
                        sourceQueue = passengerQueue;
                        passenger = sourceQueue.tryGetNextPassengerToEscort();
                    }

                    if (passenger == null) {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }

            Thread t = passenger.moveFromTo(sourceQueue, this);
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

            try {
                label.setIcon(new ImageIcon(ImageIO.read(MainGui.class.getResource("/icons/plane_fly_away.png")).getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            panel.setBackground(Color.RED);

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            //throw new RuntimeException(e);
        }


            label.setIcon(oldIcon);
            panel.setBackground(Color.GREEN);

    }

    public void plsStop(){
        isStopped = true;
    }

}
