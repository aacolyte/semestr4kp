package Entities;

import java.awt.*;

public interface IFromTo {
    public void onOut(Passenger ps);

    public void onIn(Passenger ps);

    public Component getComponent();
}
