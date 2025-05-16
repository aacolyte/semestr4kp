package Entities;

import gui.MainGui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class PassengerQueue implements IFromTo {
    private Deque<Passenger> queue;
    private MainGui mainGui;
    private JTextField field;

    public PassengerQueue(MainGui mainGui,JTextField field) {
        this.mainGui = mainGui;
        this.queue = new ArrayDeque<Passenger>();
        this.field = field;
    }

    public Passenger removeFirst(){
        if(!this.queue.isEmpty()){
            Passenger passenger = this.queue.removeFirst();
            this.field.setText(Integer.toString(this.queue.size()));
            return passenger;
        }
        return null;
    }

    public int getQueueSize() {
        return this.queue.size();
    }


    @Override
    public void onOut(Passenger ps) {

    }

    @Override
    public Component getComponent() {
        return this.field;
    }

    private boolean escortInProgress = false;
    public synchronized Passenger getNextPassengerToEscort() throws InterruptedException {
        while(queue.isEmpty() || escortInProgress) {
            wait();
        }
        escortInProgress = true;
        Passenger p = queue.removeFirst();
        field.setText(Integer.toString(queue.size()));
        return p;
    }

    public synchronized void escortFinished() {
        escortInProgress = false;
        notifyAll();
    }

    // Обновим методы addLast и onIn, чтобы notify вызывался правильно
    public synchronized void addLast(Passenger passenger) {
        this.queue.add(passenger);
        this.field.setText(Integer.toString(this.queue.size()));
        notifyAll();
    }

    @Override
    public synchronized void onIn(Passenger passenger) {
        addLast(passenger);
    }
}

