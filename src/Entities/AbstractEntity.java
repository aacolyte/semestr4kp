package Entities;

import gui.MainGui;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractEntity implements IFromTo, Runnable {
    public PassengerQueue passengerQueue;
    public MainGui mainGui;
    public Passenger passenger;
    public JLabel label;

    public AbstractEntity(PassengerQueue passengerQueue, MainGui mainGui, JLabel label) {
        this.passengerQueue = passengerQueue;
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
        return this.label;
    }
    public void showWorking(){
        int n = 10;
        int minTime = 10;// проверить подходит ли нам время
        int step = minTime/n;
        int time = (int) (minTime*(7*Math.random()));
        time/=(step*step);
        while(time > 0){
            try {
                Thread.sleep(step);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            time-=step;
        }
    }

}
