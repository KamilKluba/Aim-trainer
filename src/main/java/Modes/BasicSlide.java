package Modes;

import Data.Circle;
import Data.WindowSize;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicSlide {
    private ArrayList<Circle> arrayListCircles = new ArrayList<>();
    private int canvasX;
    private int canvasY;
    private boolean alive = true;
    private GraphicsContext graphicsContext;

    public BasicSlide(int canvasX, int canvasY, GraphicsContext graphicsContext){
        this.canvasX = canvasX;
        this.canvasY = canvasY;
        this.graphicsContext = graphicsContext;

        Random random = new Random();

        ExecutorService executor1 = Executors.newFixedThreadPool(1);
        ExecutorService executor2 = Executors.newFixedThreadPool(1);
        ExecutorService executor3 = Executors.newFixedThreadPool(1);

        Stop[] stops = new Stop[]{new Stop(0, Color.YELLOW), new Stop(1, Color.RED)};

        Runnable createCircles = (() -> {
           while(true){
               synchronized(arrayListCircles) {
                   System.out.println(arrayListCircles.size());
                   arrayListCircles.add(new Circle(Math.abs(random.nextInt() % canvasX), Math.abs(random.nextInt() % canvasY),
                           1, -1, new RadialGradient(0.63, 0.58, 0.7, 0.7,
                           0.63, true, CycleMethod.NO_CYCLE, stops)));
               }
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
            }
        });

        Runnable increaseCirclesSizes = (() -> {
            while(true){
                synchronized(arrayListCircles) {
              //      System.out.println(2);
                    for (Circle c : arrayListCircles) {
                        if (c.getR() < 30)
                            c.setR(c.getR() + 0.1);
                    }
                }
               try {
                   Thread.sleep(5);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        Runnable drawCircles = (() -> {
            while(true){
                synchronized(arrayListCircles) {
               //     System.out.println(3);
                    graphicsContext.clearRect(0, 0, canvasX, canvasY);
                    for (Circle c : arrayListCircles) {
                        graphicsContext.setFill(c.getColor());
                        graphicsContext.fillOval(c.getX() - c.getR() / 2, c.getY() - c.getR() / 2, c.getR(), c.getR());

                    }
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor1.submit(createCircles);
        executor2.submit(increaseCirclesSizes);
        executor3.submit(drawCircles);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void checkIfHit(double x, double y){
        synchronized (arrayListCircles) {
            for (Circle c : arrayListCircles)
                if (Math.sqrt(Math.pow((c.getX() - x + 50), 2) + Math.pow((c.getY() - y + 50), 2)) <= c.getR())
                    arrayListCircles.remove(c);
        }
    }
}
