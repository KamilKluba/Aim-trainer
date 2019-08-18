package Modes.Precision;

import Data.Circle;
import Modes.Mode;
import controllers.PlayWindowController;
import javafx.application.Platform;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;

public class BasicAiming extends Mode {
    private int spawnTime;

    public BasicAiming(PlayWindowController playWindowController, double spawnTime) {
        super(playWindowController);
        this.spawnTime = (int)spawnTime;

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
                        0.63, true, CycleMethod.NO_CYCLE, stops0)));
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
                    if (c.getR() < 30)
                        c.setR(c.getR() + 0.1);
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawCircles(){
        while (alive.get()) {
            Platform.runLater(() -> {
                graphicsContext.clearRect(0, 0, canvasX + 1, canvasY + 1);
                synchronized (arrayListCircles) {
                    for (Circle c : arrayListCircles) {
                        c.paint(graphicsContext);
                    }
                }
            });
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkIfHit(double x, double y) {
        boolean hit = false;
        synchronized (arrayListCircles) {
            for (Circle c : arrayListCircles)
                if (Math.sqrt(Math.pow((c.getX() - x), 2) + Math.pow((c.getY() - y), 2)) <= c.getR() / 2) {
                    arrayListCircles.remove(c);
                    hit = true;
                    break;
                }
        }
        if (!hit) {
            missedHits++;
            Platform.runLater(() -> playWindowController.getLabelResult2Value().setText("" + missedHits));
        }
    }
}

