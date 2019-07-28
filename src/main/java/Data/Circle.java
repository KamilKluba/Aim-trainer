package Data;

import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;

public class Circle {
    private int x;
    private int y;
    private double r;
    private int direction;
    private RadialGradient color;

    public Circle(int x, int y, double r, int direction, RadialGradient color) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.direction = direction;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
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
}
