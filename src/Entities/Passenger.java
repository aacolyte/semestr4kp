package Entities;

import gui.MainGui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Passenger {
    private MainGui mainGui;
    private JLabel jLabel;

    public Passenger(MainGui mainGui) {
        this.mainGui = mainGui;
        this.jLabel = new JLabel();
        jLabel.setBounds(50,380,70,70);
        this.mainGui.frame.add(jLabel);
    }

    public Thread moveFromTo(final IFromTo from, final IFromTo to) {
        Thread thread = new Thread(){
            public void run() {

                int x1From = from.getComponent().getX();
                int x2To = to.getComponent().getX();
                int distanceX = x2To - x1From;

                int y1From = from.getComponent().getY();
                int y2To = to.getComponent().getY();
                int distanceY = y2To - y1From;

                int distance = (int) Math.round(Math.sqrt(distanceX * distanceX + distanceY * distanceY));

                int averageDistance = (Passenger.this.jLabel.getWidth()+Passenger.this.jLabel.getHeight())/2;

                int stepsNumber = distance/averageDistance;
                int disX = distanceX/stepsNumber;
                int disY = distanceY/stepsNumber;

                from.onOut(Passenger.this);

                for (int i = 0, x = x1From, y = y1From ; i < stepsNumber; i++, x += disX, y += disY) {
                    try{
                        Image image = ImageIO.read(MainGui.class.getResource("/icons/gawaiMan.png"));//у нас нету песенджера
                        image = image.getScaledInstance(Passenger.this.jLabel.getWidth(), Passenger.this.jLabel.getHeight(), Image.SCALE_SMOOTH);
                        Passenger.this.jLabel.setIcon(new ImageIcon(image));
                        Passenger.this.jLabel.setBounds(x, y, Passenger.this.jLabel.getWidth(), Passenger.this.jLabel.getHeight());
                        try{
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Passenger.this.jLabel.setBounds(x, y, Passenger.this.jLabel.getWidth(), Passenger.this.jLabel.getHeight());
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                }
                Passenger.this.jLabel.setIcon(null);
                to.onIn(Passenger.this);
            }
        };
        thread.start();
        return thread;
    }
}
