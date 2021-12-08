package com.example.diehlweatherappuidesign;

/**
 * Wind - implements speed and direction attributes.
 * @author John K. Estell
 */
public class Wind {
    private Speed speed;
    private Direction direction;

    public Wind(Speed speed, Direction direction) {
        this.speed = speed;
        this.direction = direction;
    }

    public Wind() {
        this.speed = null;
        this.direction = null;

    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Speed getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {

        return "Wind{" + "speed=" + speed + ", direction=" + direction + '}';
    }
}
