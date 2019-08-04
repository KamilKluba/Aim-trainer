package Modes;

import Data.Circle;
import javafx.scene.canvas.Canvas;
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
    protected final List<Circle> arrayListCircles = Collections.synchronizedList(new ArrayList<Circle>());
    protected int canvasX;
    protected int canvasY;
    protected int totalCircles;
    protected int hitCircles;
    protected double hitRatio;
    protected int spawnTime;
    protected double averageHitTime;
    protected AtomicBoolean alive = new AtomicBoolean(true);
    protected Random random = new Random();
    protected GraphicsContext graphicsContext = null;
    protected Stop[] stops = new Stop[]{new Stop(0, Color.YELLOW), new Stop(1, Color.RED)};
    protected Stop[] stops2 = new Stop[]{new Stop(0, Color.GREEN), new Stop(1, Color.BLUE)};

    protected ExecutorService executor1 = Executors.newFixedThreadPool(1);
    protected ExecutorService executor2 = Executors.newFixedThreadPool(1);
    protected ExecutorService executor3 = Executors.newFixedThreadPool(1);

    public Mode(Canvas canvas){
        this.canvasX = (int)canvas.getWidth();
        this.canvasY = (int)canvas.getHeight();
        this.graphicsContext = canvas.getGraphicsContext2D();

        totalCircles = 0;
        hitCircles = 0;
        hitRatio = 0;
        spawnTime = 1000;
        averageHitTime = 0;
    }

    public boolean isAlive() {
        return alive.get();
    }

    public void setAlive(boolean alive) {
        this.alive.set(alive);
    }

}
