package Modes.Precision;

import Data.Circle;
import Modes.Mode;
import controllers.PlayWindowController;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;

import java.util.ArrayList;

public class StayWithMe extends Mode {
    private Circle circle;
    private ArrayList<Line> arrayListLines = new ArrayList<Line>();
    private double circleSize;
    private double mouseX;
    private double mouseY;
    private boolean isOutOfLine = false;
    private int defeatsAmount = 0;
    private int victoriesAmount = 0;

    public StayWithMe(PlayWindowController playWindowController, double circleSize) {
        super(playWindowController);
        this.circleSize = circleSize;

        circle = new Circle(100, canvasY / 2, circleSize, -1,
                new RadialGradient(0.63, 0.58, 0.7, 0.7,
                        0.63, true, CycleMethod.NO_CYCLE, stops));
        circle.paint(graphicsContext);


        boolean lineUp = random.nextBoolean();
        int currentX = 100;
        int currentY = canvasY / 2;
        for(int i = 0; i < 10; i++){
            if(arrayListLines.size() % 2 == 0){
                int nextY;
                if(!lineUp){
                    nextY = random.nextInt(currentY - 150) + 100;
                }
                else{
                    nextY = random.nextInt(canvasY - currentY - 150) + 100 + currentY;
                }
                arrayListLines.add(new Line(currentX, currentY, currentX, nextY));
                currentY = nextY;
                lineUp = !lineUp;
            }
            else{
                //canvasX - currentX
                int nextX = random.nextInt(100) + 100 + currentX;
                arrayListLines.add(new Line(currentX, currentY, nextX, currentY));
                currentX = nextX;
            }
        }
        //arrayListLines.add(new Line(100, canvasY / 2, 700, 500));
        for(Line line : arrayListLines) {
            Platform.runLater(() -> {
                graphicsContext.setStroke(Color.WHITE);
                graphicsContext.strokeLine(line.x1, line.y1, line.x2, line.y2);
            });
        }
    }

    public void checkIfCircleIsOnLine(double x, double y){
        if(!isOutOfLine) {
            boolean isInside = false;
            for (Line l : arrayListLines) {
                if (l.isOnTheLine(circle.getX(), circle.getY(), circleSize)) {
                    isInside = true;
                    break;
                }
            }

            if (isInside) {
                circle.setColor(stops);
            } else {
                circle.setColor(stops2);
                isOutOfLine = true;
                defeatsAmount++;
                Platform.runLater(() -> playWindowController.getLabelResult1Value().setText("" + defeatsAmount));
            }

            circle.setX(circle.getX() - mouseX + x);
            circle.setY(circle.getY() - mouseY + y);
            mouseX = x;
            mouseY = y;

            Platform.runLater(() -> graphicsContext.clearRect(0, 0, canvasX + 1, canvasY + 1));
            circle.paint(graphicsContext);
            for (Line l : arrayListLines) {
                l.paint(graphicsContext);
            }
        }
    }

    public void saveMousePosition(double x, double y){
        mouseX = x;
        mouseY = y;
    }

    public void resetCirclePosition(){
        if(isOutOfLine) {
            circle.setX(100);
            circle.setY(canvasY / 2);
            circle.setColor(stops);
            Platform.runLater(() -> graphicsContext.clearRect(0, 0, canvasX + 1, canvasY + 1));
            circle.paint(graphicsContext);
            for (Line l : arrayListLines) {
                l.paint(graphicsContext);
            }
        }
        isOutOfLine = false;
    }

    private class Line{
        double x1;
        double y1;
        double x2;
        double y2;
        double a;
        double b;
        double c;
        boolean horizontal;
        boolean vertical;

        public Line(double x1, double y1, double x2, double y2){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            if(x1 != x2 && y1 != y2) {
                this.a = (y2 - y1) / (x2 - x1);
                this.b = -1;
                if (a != 0) {
                    this.c = y1 - (a * x1);
                } else {
                    c = 0;
                }
                horizontal = false;
                vertical = false;
            }
            else if(x1 == x2){
                horizontal = false;
                vertical = true;
                a = x1;
                b = 0;
                c = 0;
            }
            else if(y1 == y2){
                vertical = false;
                horizontal = true;
                a = 0;
                b = y1;
                c = 0;
            }
        }

        public boolean isOnTheLine(double x, double y, double r){
            boolean isUnderX1 = false;
            boolean isUnderX2 = false;
            boolean isUnderTheLine = false;
            //if < 0, count distance to p1, > 1, to p2, <0;1>, to line
            double whereToCount = ((x2 - x1) * (x - x1) + (y2 - y1) * (y - y1)) / (Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));

            if(whereToCount < 0)
                isUnderX1 = Math.sqrt(Math.pow((x - x1), 2) + Math.pow((y - y1), 2)) < r / 2;
            else if(whereToCount > 1)
                isUnderX2 =  Math.sqrt(Math.pow((x - x2), 2) + Math.pow((y - y2), 2)) < r / 2;
            else {
                if(!vertical && !horizontal)
                    isUnderTheLine = Math.abs(a * x + b * y + c) / Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)) < r / 2;
                if(vertical)
                    isUnderTheLine = Math.abs(x - x1) < r / 2;
                if(horizontal)
                    isUnderTheLine = Math.abs(y - y1) < r / 2;
            }

            return isUnderX1 || isUnderX2 || isUnderTheLine;
        }

        public void paint(GraphicsContext graphicsContext){
            Platform.runLater(() -> graphicsContext.strokeLine(x1, y1, x2, y2));
        }
    }
}
