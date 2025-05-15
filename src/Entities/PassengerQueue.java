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
    public void addLast(Passenger passenger) {
        this.queue.add(passenger);
        this.field.setText(Integer.toString(this.queue.size()));
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
    public void onIn(Passenger passenger) {
        synchronized (this){
            addLast(passenger);
            notify();
            return;
        }
    }

    @Override
    public Component getComponent() {
        return this.field;
    }

}
