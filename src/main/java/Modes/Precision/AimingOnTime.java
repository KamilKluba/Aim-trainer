package Modes.Precision;

import Data.Circle;
import Modes.Mode;
import controllers.PlayWindowController;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;

public class AimingOnTime extends Mode {
    private int spawnTime;
    private int lifeTime;

    public AimingOnTime(PlayWindowController playWindowController, double spawnTime, double lifeTime) {
        super(playWindowController);
        this.spawnTime = (int)spawnTime;
        this.lifeTime = (int)lifeTime / 600;

        Runnable createCircles = (() -> createCircles());
        Runnable increaseCirclesSizes = (() -> modifyCircles());
        Runnable drawCircles = (() -> drawCircles());

        executor1.submit(createCircles);
        executor2.submit(increaseCirclesSizes);
        executor3.submit(drawCircles);
    }

    public void createCircles(){
        while (alive.get()) {
            synchronized (arrayListCircles){
                arrayListCircles.add(new Circle(30 + Math.abs(random.nextInt() % (canvasX - 60)),
                        30 + Math.abs(random.nextInt() % (canvasY - 60)),
                        0, -1, new RadialGradient(0.63, 0.58, 0.7, 0.7,
                        0.63, true, CycleMethod.NO_CYCLE, stops)));
            }
            totalCircles++;
            Platform.runLater(() -> playWindowController.getLabelResult1Value().setText("" + totalCircles));
            try {
                Thread.sleep(spawnTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void modifyCircles(){
        while (alive.get()) {
            synchronized (arrayListCircles){
                for (Circle c : arrayListCircles) {
                    if (c.isRising()) {
                        c.setR(c.getR() + 0.1);
                        if(c.getR() >= 30){
                            c.setRising(false);
                        }
                    }
                    else {
                        c.setR(c.getR() - 0.1);
                        if(c.getR() == 0){
                            arrayListCircles.remove(c);
                        }
                    }
                }
            }
            try {
                Thread.sleep(lifeTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawCircles(){
        while (alive.get()) {
            Platform.runLater(() -> {
                graphicsContext.clearRect(0, 0, canvasX, canvasY);
                synchronized (arrayListCircles) {
                    for (Circle c : arrayListCircles) {
                        graphicsContext.setFill(c.getColor());
                        graphicsContext.fillOval(c.getX() - c.getR() / 2, c.getY() - c.getR() / 2, c.getR(), c.getR());
                    }
                }
            });
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkIfHit(double x, double y) {
        synchronized (arrayListCircles) {
            for (Circle c : arrayListCircles)
                if (Math.sqrt(Math.pow((c.getX() - x), 2) + Math.pow((c.getY() - y), 2)) <= c.getR() / 2) {
                    arrayListCircles.remove(c);
                    break;
                }
        }
        hitCircles++;
        Platform.runLater(() -> playWindowController.getLabelResult2Value().setText("" + totalCircles));
    }
}

