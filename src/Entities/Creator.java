package Entities;

import com.sun.tools.javac.Main;
import gui.MainGui;

import javax.swing.*;

public class Creator extends AbstractEntity{

    private JTextField passengerCount;
    private int count = 0;

    public Creator(MainGui mainGui, JLabel jLabel, PassengerQueue passengerQueue, PassengerQueue passengerQueue2,
                   JTextField passengerCount) {
        super(passengerQueue,passengerQueue2,mainGui,jLabel);
        this.passengerCount = passengerCount;
        this.count = Integer.valueOf(passengerCount.getText());
    }

    @Override
    public void run() {
        while(count>0){
            --count;
            this.passengerCount.setText(Integer.toString(count));
            this.passenger = new Passenger(this.mainGui);
            int a = passengerQueue.getQueueSize();
            int b = passengerQueue2.getQueueSize();
            if (a > b){
                final Thread thread = this.passenger.moveFromTo((IFromTo) this, (IFromTo) this.passengerQueue2);
                try{
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                final Thread thread = this.passenger.moveFromTo((IFromTo) this, (IFromTo) this.passengerQueue);
                try{
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
