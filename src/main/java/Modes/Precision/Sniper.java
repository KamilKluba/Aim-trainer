package Modes.Precision;

import Data.Circle;
import Modes.Mode;
import controllers.PlayWindowController;
import javafx.application.Platform;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;

public class Sniper extends Mode {
    private Circle circle;
    private double circleSpeed;

    public Sniper(PlayWindowController playWindowController, double circleSize, double circleSpeed) {
        super(playWindowController);
        this.circleSpeed = circleSpeed;

        circle = new Circle(canvasX / 2, canvasY / 2, circleSize, -1,
                new RadialGradient(0.63, 0.58, 0.7, 0.7,
                        0.63, true, CycleMethod.NO_CYCLE, stops));

        circle.paint(graphicsContext);

        executor1.submit(() -> moveCircle());
    }

    public void moveCircle(){
        while(alive.get()) {
            synchronized (circle) {
                double circleX = circle.getX();
                double circleY = circle.getY();
                double circleR = circle.getR();

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

                circle.setX(circleX + circle.getShiftX() * circleSpeed);
                circle.setY(circleY + circle.getShiftY() * circleSpeed);

                Platform.runLater(() -> graphicsContext.clearRect(0, 0, canvasX + 1, canvasY + 1));
                circle.paint(graphicsContext);
            }
            try{
                Thread.sleep(5);
            }catch(InterruptedException e){e.printStackTrace();}
        }
    }

    public void checkIfHit(double x, double y) {
        synchronized (circle) {
            if (Math.sqrt(Math.pow((circle.getX() - x), 2) + Math.pow((circle.getY() - y), 2)) <= circle.getR() / 2) {
                circle.setX(random.nextInt(canvasX - 60) + 30);
                circle.setY(random.nextInt(canvasY - 60) + 30);
                circle.setDirection(random.nextInt() % 360);
                hitCircles++;
                Platform.runLater(() -> playWindowController.getLabelResult1Value().setText("" + hitCircles));
            } else {
                missedHits++;
                Platform.runLater(() -> playWindowController.getLabelResult2Value().setText("" + missedHits));
            }
        }
    }
}
