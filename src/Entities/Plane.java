package Entities;

import gui.MainGui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Plane extends AbstractEntity {
    private Counter counter;
    private JPanel panel;
    String[] images = new String[]{
            "/icons/litak1",
            "/icons/litak2",
            "/icons/litak3"};
    public Plane(MainGui mainGui, JLabel label, PassengerQueue passengerQueue,
                 Counter counter, JPanel panel) {
        super(passengerQueue,mainGui,label);
        this.counter = counter;
        this.panel = panel;
    }


    @Override
    public void run() {
        while (this.mainGui.isPlaneFlyAway() ||
        this.passengerQueue.getQueueSize()>0) {
            synchronized (this.passengerQueue){
                while(this.passengerQueue.getQueueSize()<=0){
                    try {
                        this.passengerQueue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                this.passenger = this.passengerQueue.removeFirst();
                this.passengerQueue.notify();
            }
            Thread thread = this.passenger.moveFromTo(this.passengerQueue,this);
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.showWorking();
            this.passenger.moveFromTo(this,this.counter);
        }
    }

    public void showWorking() {
        URL url = getClass().getResource("/images/litak1.png");
        BufferedImage image = null;

        try{
            image = ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image image1 = image.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(image1);
        label.setIcon(icon);
        panel.setBackground(Color.RED);

        Image image2 = null;

        int minTime = 1700;
        int step = minTime/10;
        minTime = (int) (minTime*(10*Math.random()));
        int i=0;
        int change_image = (minTime/2);
        while (minTime>0){
            try {
                Thread.sleep(step);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(minTime<=change_image){
                try{
                    image2 = ImageIO.read(getClass().getResource(images[i])).getScaledInstance(
                            label.getWidth(),label.getHeight(),Image.SCALE_SMOOTH);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                label.setIcon(new ImageIcon(image2));
                i++;
                if(i==images.length){
                    i=0;
                }
                minTime-=step;
            }

            try {
                image2 = ImageIO.read(getClass().getResource(images[i])).getScaledInstance(
                        label.getWidth(),label.getHeight(),Image.SCALE_SMOOTH);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            label.setIcon(new ImageIcon(image2));
            panel.setBackground(Color.GREEN);
        }
    }


}
