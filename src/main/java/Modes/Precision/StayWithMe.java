package Modes.Precision;

import Data.Circle;
import Modes.Mode;
import controllers.PlayWindowController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class StayWithMe extends Mode {
    private Circle circle;
    private ArrayList<Line> arrayListLines = new ArrayList<Line>();
    private double circleSize;

    public StayWithMe(PlayWindowController playWindowController, double circleSize){
        super(playWindowController);
        this.circleSize = circleSize;

        arrayListLines.add(new Line(200, 200, 400, 300));

        Line line = arrayListLines.get(0);
        graphicsContext.setStroke(Color.WHITE);
        graphicsContext.strokeLine(line.x1, line.y1, line.x2, line.y2);
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
            if(x1 != x2 || y1 != x2) {
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
                horizontal = true;
                vertical = false;
                a = x1;
                b = 0;
                c = 0;
            }
            else if(y1 == y2){
                vertical = true;
                horizontal = false;
                a = 0;
                b = y1;
                c = 0;
            }
            System.out.println(a + " " + b + " " + c);
        }
    }
}
