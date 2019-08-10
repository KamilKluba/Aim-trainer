package Modes.Precision;

import Data.Circle;
import Modes.Mode;
import com.sun.javafx.tk.Toolkit;
import controllers.PlayWindowController;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GotYa extends Mode {
    private Circle circle;
    private double circleSpeed;
    private int circleAgility;
    private int halfCircleAgility;
    private double circleSize;
    private double lastX;
    private double lastY;

    public GotYa(PlayWindowController playWindowController, double circleSpeed, double circleAgility, double circleSize) {
        super(playWindowController);
        this.circleSpeed = circleSpeed;
        this.halfCircleAgility = (int)circleAgility / 2;
        this.circleAgility = (int)circleAgility + 1;
        this.circleSize = circleSize;

        circle = new Circle(canvasX / 2, canvasY / 2, circleSize,  Math.abs(random.nextInt() % 360),
                new RadialGradient(0.63, 0.58, 0.7, 0.7,
                0.63, true, CycleMethod.NO_CYCLE, stops));

        Runnable moveCircle = (() -> moveCircle());
        Runnable drawCircle = (() -> drawCircle());
        Runnable checkIfInside2 = (() -> checkIfInside2());
        executor1.submit(moveCircle);
        executor2.submit(drawCircle);
        executor3.submit(checkIfInside2);
    }

    private void moveCircle(){
        while(alive.get()){
            synchronized(circle) {
                double circleX = circle.getX();
                double circleY = circle.getY();
                double circleR = circle.getR();

                if(circle.getDecreasedChangeDirectionDelay() <= 0) {
                    circle.setDirection(random.nextInt(circleAgility) - halfCircleAgility);
                    circle.setChangeDirectionDelay(random.nextInt(circleAgility));
                }

                if (circleX <= circleR) {
                    circle.setDirection(180 + (270 - circle.getDirection()) * 2);
                }
                else if(circleX >= canvasX - circleR) {
                    circle.setDirection(180 + (90 - circle.getDirection()) * 2);
                }
                else if(circleY <= circleR){
                    circle.setDirection(180 + (180 - circle.getDirection()) * 2);
                }
                else if(circleY >= canvasY - circleR){
                    circle.setDirection(180 - circle.getDirection() * 2);
                }

                circle.setX(circleX + circle.getShiftX() * (0.5 + circleSpeed / 5));
                circle.setY(circleY + circle.getShiftY() * (0.5 + circleSpeed / 5));
            }
            try{
                Thread.sleep(5);
            }catch(InterruptedException e){e.printStackTrace();}
        }
    }

    private void drawCircle(){
        while(alive.get()) {
            double circleX;
            double circleY;
            double circleR;

            synchronized(circle){
                circleX = circle.getX();
                circleY = circle.getY();
                circleR = circle.getR();
            }

            Platform.runLater(() -> {
                graphicsContext.clearRect(circleX - circleR / 2 - 20, circleY - circleR / 2 - 20,
                        circleX - circleR / 2 + 40, circleY - circleR / 2 + 40);
                graphicsContext.setFill(circle.getColor());
                graphicsContext.fillOval(circleX - circleR / 2, circleY - circleR / 2,
                        circleR, circleR);
            });
            try{
                Thread.sleep(1);
            }catch(InterruptedException e){e.printStackTrace();}
        }
    }

    //this method fixes a bug, where circle act like a mouse was inside it after the mouse stops moving being
    //inside the circle
    private void checkIfInside2(){
        while(alive.get()) {
            synchronized(circle){
                if (Math.sqrt(Math.pow((circle.getX() - lastX), 2) + Math.pow((circle.getY() - lastY), 2)) <= circle.getR() / 2) {
                    circle.setColor(new RadialGradient(0.63, 0.58, 0.7, 0.7,
                            0.63, true, CycleMethod.NO_CYCLE, stops2));
                }
                else{
                    circle.setColor(new RadialGradient(0.63, 0.58, 0.7, 0.7,
                            0.63, true, CycleMethod.NO_CYCLE, stops));
                }
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkIfInside(double x, double y){
        synchronized(circle) {
            lastX = x;
            lastY = y;
            if (Math.sqrt(Math.pow((circle.getX() - x), 2) + Math.pow((circle.getY() - y), 2)) <= circle.getR() / 2) {
                circle.setColor(new RadialGradient(0.63, 0.58, 0.7, 0.7,
                        0.63, true, CycleMethod.NO_CYCLE, stops2));
            }
            else{
                circle.setColor(new RadialGradient(0.63, 0.58, 0.7, 0.7,
                        0.63, true, CycleMethod.NO_CYCLE, stops));
            }
        }
    }


}
