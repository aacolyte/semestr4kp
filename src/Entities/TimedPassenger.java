package Entities;

public class TimedPassenger {
    public final Passenger passenger;
    public final long enqueueTime;

    public TimedPassenger(Passenger passenger) {
        this.passenger = passenger;
        this.enqueueTime = System.currentTimeMillis();
    }

    public boolean waitedLongEnough() {
        return System.currentTimeMillis() - enqueueTime >= 3000;
    }
}
