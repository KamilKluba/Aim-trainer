package Modes.Precision;

import Data.Circle;
import Modes.Mode;
import com.sun.javafx.tk.Toolkit;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GotYa extends Mode {
    private Circle circle;
    double circleSpeed = 3;

    public GotYa(Canvas canvas, double circleSpeed) {
        super(canvas);
        this.circleSpeed = circleSpeed;

        circle = new Circle(canvasX / 2, canvasY / 2, 30,  Math.abs(random.nextInt() % 360),
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
            synchronized (circle) {
                double circleX = circle.getX();
                double circleY = circle.getY();
                double circleR = circle.getR();

                if(circle.getDecreasedChangeDirectionDelay() <= 0) {
                    circle.setDirection(random.nextInt(91) - 46);
                    circle.setChangeDirectionDelay(random.nextInt(101));
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

            synchronized (circle){
                circleX = circle.getX();
                circleY = circle.getY();
                circleR = circle.getR();
            }

            Platform.runLater(() -> {
                graphicsContext.clearRect(0, 0, canvasX, canvasY);
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
            System.out.println(MouseInfo.getPointerInfo().getLocation());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkIfInside(double x, double y){
        synchronized (circle) {
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
