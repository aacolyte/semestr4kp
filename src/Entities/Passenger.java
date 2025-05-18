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

                int averageSize = (Passenger.this.jLabel.getWidth()+Passenger.this.jLabel.getHeight())/2;

                int stepsNumber = distance/averageSize;
                int stepX = distanceX/stepsNumber;
                int stepY = distanceY/stepsNumber;


                Image image = null;
                try {
                    image = ImageIO.read(MainGui.class.getResource("/icons/gawaiMan.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                image = image.getScaledInstance(Passenger.this.jLabel.getWidth(), Passenger.this.jLabel.getHeight(), Image.SCALE_SMOOTH);
                Passenger.this.jLabel.setIcon(new ImageIcon(image));


                from.onOut(Passenger.this);


                for (int i = 0, x = x1From, y = y1From ; i < stepsNumber; i++, x += stepX, y += stepY) {
                        Passenger.this.jLabel.setBounds(x, y, Passenger.this.jLabel.getWidth(), Passenger.this.jLabel.getHeight());
                        try{
                            Thread.sleep(800);
                        } catch (InterruptedException e) {
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

    public JLabel getLabel() {
        return this.jLabel;
    }
    public void moveUpAndDisappear(){
        JLabel label = this.getLabel();
        int x = label.getX();
        int y = label.getY();
        for(int i = 0; i < 3; i++){
            y -= 100;
            final int finalY = y;
            SwingUtilities.invokeLater(() -> label.setLocation(x, finalY));
            try{
                Image image = ImageIO.read(MainGui.class.getResource("/icons/gawaiMan.png"));
                image = image.getScaledInstance(Passenger.this.jLabel.getWidth(), Passenger.this.jLabel.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(image);
                SwingUtilities.invokeLater(() -> {label.setIcon(icon); label.setBounds(x, finalY, Passenger.this.jLabel.getWidth(), Passenger.this.jLabel.getHeight());});
                Thread.sleep(500);
            } catch(IOException e) {

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        label.setIcon(null);
        label.setVisible(false);
    }
}
