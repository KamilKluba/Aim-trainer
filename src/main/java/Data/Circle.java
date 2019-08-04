package Data;

import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;

public class Circle {
    private double x;
    private double y;
    private double r;
    private int direction;
    private RadialGradient color;
    private boolean isRising = true;

    public Circle(double x, double y, double r, int direction, RadialGradient color) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.direction = direction;
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public RadialGradient getColor() {
        return color;
    }

    public void setColor(RadialGradient color) {
        this.color = color;
    }

    public boolean isRising() {
        return isRising;
    }

    public void setRising(boolean rising) {
        isRising = rising;
    }
}
