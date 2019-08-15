package Modes;

import Data.Circle;
import controllers.PlayWindowController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Mode {
    protected PlayWindowController playWindowController;
    protected final List<Circle> arrayListCircles = Collections.synchronizedList(new ArrayList<Circle>());
    protected int canvasX;
    protected int canvasY;
    protected int totalCircles;
    protected int hitCircles;
    protected int missedHits;
    protected double hitRatio;
    protected double averageHitTime;
    protected AtomicBoolean alive = new AtomicBoolean(true);
    protected Random random = new Random();
    protected GraphicsContext graphicsContext = null;
    protected Stop[] stops = new Stop[]{new Stop(0, Color.YELLOW), new Stop(1, Color.RED)};
    protected Stop[] stops2 = new Stop[]{new Stop(0, Color.GREEN), new Stop(1, Color.BLUE)};

    protected ExecutorService executor1 = Executors.newFixedThreadPool(1);
    protected ExecutorService executor2 = Executors.newFixedThreadPool(1);
    protected ExecutorService executor3 = Executors.newFixedThreadPool(1);

    public Mode(PlayWindowController playWindowController){
        this.playWindowController = playWindowController;
        this.canvasX = (int)playWindowController.getCanvas().getWidth();
        this.canvasY = (int)playWindowController.getCanvas().getHeight();
        this.graphicsContext = playWindowController.getCanvas().getGraphicsContext2D();

        totalCircles = 0;
        hitCircles = 0;
        missedHits = 0;
        hitRatio = 0;
        averageHitTime = 0;
    }

    public boolean isAlive() {
        return alive.get();
    }

    public void setAlive(boolean alive) {
        this.alive.set(alive);
        new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            graphicsContext.clearRect(0, 0, canvasX, canvasY);
        }).start();
    }

}
