package Entities;

import gui.MainGui;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractEntity implements IFromTo, Runnable {
    public PassengerQueue passengerQueue;
    public MainGui mainGui;
    public Passenger passenger;
    public JLabel label;
    public PassengerQueue passengerQueue2;

    public AbstractEntity(PassengerQueue passengerQueue, PassengerQueue passengerQueue2,MainGui mainGui, JLabel label) {
        this.passengerQueue = passengerQueue;
        this.passengerQueue2 = passengerQueue2;
        this.mainGui = mainGui;
        this.label = label;
    }

    @Override
    public void onOut(Passenger ps) {

    }

    @Override
    public void onIn(Passenger ps) {

    }

    @Override
    public Component getComponent() {
        return label;
    }
}
