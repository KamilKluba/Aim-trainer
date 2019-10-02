package Data;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class Circle {
    private double x;
    private double y;
    private double r;
    private int direction;
    private double shiftX;
    private double shiftY;
    private int changeDirectionDelay;
    private RadialGradient color;
    private boolean isRising = true;

    public Circle(double x, double y, double r, int direction, Stop[] color) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.direction = direction;
        this.shiftX = Math.sin(Math.PI / 180 * direction) / 2;
        this.shiftY = Math.cos(Math.PI / 180 * direction) / 2;
        this.changeDirectionDelay = 0;
        setColor(color);
    }

    public void paint(GraphicsContext graphicsContext){
        Platform.runLater(() -> {
            graphicsContext.setFill(color);
            graphicsContext.fillOval(x - r / 2, y - r / 2, r, r);
        });
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

    public void setDirection(int jump) {
        this.direction += jump;
       // if(direction > 360) direction = direction % 360;
       // else if(direction < 0) direction = 360 + direction;
        this.shiftX = Math.sin(Math.PI / 180 * direction) / 2;
        this.shiftY = Math.cos(Math.PI / 180 * direction) / 2;
    }

    public RadialGradient getColor() {
        return color;
    }

    public void setColor(Stop[] stops) {
        this.color = new RadialGradient(0.63, 0.58, 0.7, 0.7,
                0.63, true, CycleMethod.NO_CYCLE, stops);
    }

    public boolean isRising() {
        return isRising;
    }

    public void setRising(boolean rising) {
        isRising = rising;
    }

    public double getShiftX() {
        return shiftX;
    }

    public double getShiftY() {
        return shiftY;
    }

    public int getDecreasedChangeDirectionDelay() {
        changeDirectionDelay--;
        return changeDirectionDelay;
    }

    public void setChangeDirectionDelay(int changeDirectionDelay) {
        this.changeDirectionDelay = changeDirectionDelay;
    }
}
