package Entities;

import javax.swing.*;
import java.awt.*;

public class Counter implements IFromTo {
    private int counter;
    private JTextField jTextField;

    public Counter() {
        this.counter = 0;
        this.JTextField = jTextField;
    }

    @Override
    public void onOut(Passenger passenger) {
        // TODO Auto-generated method stub
        // как-будто тут чето должно быть
    }
    @Override
    public void onIn(Passenger passenger) {
        setCount(++this.counter);
    }
    @Override
    public Component getComponent() {
        return this.jTextField;
    }

    public int getCount(){
        return counter;
    }
    public void setCount(int counter){
        this.counter = counter;
        jTextField.setText("" + counter);
    }
}
