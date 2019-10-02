package Modes.Precision;

import Data.Circle;
import Modes.Mode;
import controllers.PlayWindowController;
import javafx.application.Platform;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;

import java.util.ArrayList;

public class DontTouchMe extends Mode {
    private Circle circle;
    private double circleSpeed;
    private ArrayList<CircleRow> circleRows = new ArrayList<CircleRow>();
    private double mouseX;
    private double mouseY;
    private boolean hit = false;

    public DontTouchMe(PlayWindowController playWindowController, double circleSize, int obstaclesNumber) {
        super(playWindowController);
        this.circleSpeed = circleSpeed;

        circle = new Circle(canvasX / 2, canvasY / 2, circleSize, -1, stops0);
        circle.paint(graphicsContext);

        for(int i = 0; i < 5; i++){
            circleRows.add(new CircleRow(i % 2 == 0));
        }

        for(CircleRow cr : circleRows){
            cr.paint();
        }

       // executor1.submit(() -> moveCircle());
    }

    public void moveCircle(double x, double y){
        synchronized (circle) {
            circle.setX(circle.getX() - mouseX + x);
            circle.setY(circle.getY() - mouseY + y);
        }
        mouseX = x;
        mouseY = y;

        circle.paint(graphicsContext);

        checkIfHit();
    }

    public void saveMousePosition(double x, double y){
        mouseX = x;
        mouseY = y;
    }

    public void resetCirclePosition(){
        if(hit){
            hit = false;
        }
    }

    public void checkIfHit() {
        synchronized (circle) {
            double circleX = circle.getX();
            double circleY = circle.getY();
            double circleR = circle.getR();
        }
    }

    private class CircleRow{
        ArrayList<Circle> arrayListCircles = new ArrayList<Circle>();
        double speed = random.nextInt(10);
        double firstCircleY;
        boolean moving;
        int CIRCLES_AMOUNT = 5;

        public CircleRow(boolean moving){
            this.moving = moving;

            int x = random.nextInt(canvasX - 200 - CIRCLES_AMOUNT * 30) + 200;

            if(moving){
                int y = random.nextInt(canvasY);
                for(int i = 0; i < CIRCLES_AMOUNT; i++){
                    arrayListCircles.add(new Circle(x + i * 30, y, 30, 0, stops2));
                }
            }
            else{
                boolean onTop = random.nextBoolean();

                for(int i = 0; i < CIRCLES_AMOUNT; i++){
                    if(onTop) {
                        arrayListCircles.add(new Circle(x, i * 30 + 15, 30, 0, stops2));
                    }
                    else{
                        arrayListCircles.add(new Circle(x, canvasY - i * 30 - 15, 30, 0, stops2));
                    }
                }
            }
        }

        public void paint(){
            for(Circle c : arrayListCircles){
                c.paint(graphicsContext);
            }
        }
    }
}
