package Entities;

import gui.MainGui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class PassengerQueue implements IFromTo {
    private Deque<TimedPassenger> queue;
    private MainGui mainGui;
    private JTextField field;

    public PassengerQueue(MainGui mainGui,JTextField field) {
        this.mainGui = mainGui;
        this.queue = new ArrayDeque<TimedPassenger>();
        this.field = field;
    }



    public int getQueueSize() {
        return queue.size();
    }


    @Override
    public void onOut(Passenger ps) {

    }

    @Override
    public Component getComponent() {
        return field;
    }



    public synchronized boolean isEscortInProgress() {
        return escortInProgress;
    }



    private boolean escortInProgress = false;

    public synchronized void escortFinished() {
        escortInProgress = false;
        notifyAll();
    }

    public synchronized void addLast(Passenger passenger) {
        //synchronized (this) {
            queue.add(new TimedPassenger(passenger));
            field.setText(Integer.toString(queue.size()));
            notifyAll();
        //}
    }

    @Override
    public synchronized void onIn(Passenger passenger) {
        addLast(passenger);
    }


    public synchronized Passenger tryGetNextPassengerToEscort() {
        if (!queue.isEmpty() && !escortInProgress) {
            TimedPassenger timedPassenger = queue.peekFirst();
            if (timedPassenger.waitedLongEnough()) {
                escortInProgress = true;
                queue.removeFirst();
                field.setText(Integer.toString(queue.size()));
                return timedPassenger.passenger;
            }
        }
        return null;
    }

}

