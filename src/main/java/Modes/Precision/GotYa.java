package Modes.Precision;

import Data.Circle;
import Modes.Mode;
import controllers.PlayWindowController;
import javafx.application.Platform;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;

import java.text.DecimalFormat;

public class GotYa extends Mode {
    private Circle circle;
    private double circleSpeed;
    private int circleAgility;
    private int halfCircleAgility;
    private double circleSize;
    private double lastX;
    private double lastY;
    private double currentGain = 1;
    private double highestGain = 1;
    private double score = 0;
    private DecimalFormat decimalFormatHighestGain = new DecimalFormat("##0.00");
    private DecimalFormat decimalFormatScore = new DecimalFormat("##0.0");

    public GotYa(PlayWindowController playWindowController, double circleSpeed, double circleAgility, double circleSize) {
        super(playWindowController);
        this.circleSpeed = circleSpeed;
        this.halfCircleAgility = (int)circleAgility / 2;
        this.circleAgility = (int)circleAgility + 1;
        this.circleSize = circleSize;

        circle = new Circle(canvasX / 2, canvasY / 2, circleSize,  Math.abs(random.nextInt() % 360), stops0);

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

            //not using circle.paint(gc) because this way circle is faster available for other threads
            Platform.runLater(() -> {
                graphicsContext.clearRect(circleX - circleR, circleY - circleR,
                        circleR * 2, circleR * 2);
                graphicsContext.setFill(circle.getColor());
                graphicsContext.fillOval(circleX - circleR / 2, circleY - circleR / 2,
                        circleR, circleR);
            });
            try{
                Thread.sleep(1);
            }catch(InterruptedException e){e.printStackTrace();}
        }
    }

    //this method fixes a bug, where circle act like a mouse was inside it after the mouse stops0 moving being
    //inside the circle
    private void checkIfInside2(){
        while(alive.get()) {
            synchronized(circle){
                if (Math.sqrt(Math.pow((circle.getX() - lastX), 2) + Math.pow((circle.getY() - lastY), 2)) <= circle.getR() / 2) {
                    circle.setColor(stops1);
                }
                else{
                    circle.setColor(stops0);
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
        synchronized (circle) {
            lastX = x;
            lastY = y;
            if (Math.sqrt(Math.pow((circle.getX() - x), 2) + Math.pow((circle.getY() - y), 2)) <= circle.getR() / 2) {
                circle.setColor(stops1);
                currentGain += 0.01;
                score += currentGain;
            } else {
                circle.setColor(stops0);
                currentGain = 1;
            }
        }
        Platform.runLater(() -> playWindowController.getLabelResult1Value().setText("" + decimalFormatScore.format(score)));
        if (currentGain > highestGain) {
            Platform.runLater(() -> playWindowController.getLabelResult2Value().setText("" + decimalFormatHighestGain.format(highestGain)));
            highestGain = currentGain;
        }
    }
}
