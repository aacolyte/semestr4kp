package Entities;

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
            passengerCount.setText(Integer.toString(count));
            this.passenger = new Passenger(mainGui);
            int a = passengerQueue.getQueueSize();
            int b = passengerQueue2.getQueueSize();

            if(a >=4 && b>=4){
                Thread thread = new Thread(() -> {passenger.moveUpAndDisappear();});
                thread.start();
                threadJoin(thread);
                continue;
            }

            PassengerQueue targetQueue = (a>b)?passengerQueue2:passengerQueue;
            Thread thread = this.passenger.moveFromTo(this,targetQueue);
            threadJoin(thread);


        }
    }
    private void threadJoin(Thread thread){
        try{
            //Thread.sleep(2000);
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}