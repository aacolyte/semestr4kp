package Entities;

import javax.swing.*;
import java.awt.*;

public class Counter implements IFromTo {
    private int cnt;
    private JTextField jTextField;

    public Counter(JTextField jTextField) {
        this.cnt = 0;
        this.jTextField = jTextField;
    }

    @Override
    public void onOut(Passenger passenger) {
    }
    @Override
    public void onIn(Passenger passenger) {
        setCount(++cnt);
    }
    @Override
    public Component getComponent() {
        return jTextField;
    }

    public void setCount(int cnt){
        this.cnt = cnt;
        jTextField.setText("" + cnt);
    }
}
