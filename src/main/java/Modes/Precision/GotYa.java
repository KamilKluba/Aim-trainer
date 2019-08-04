package Modes.Precision;

import Data.Circle;
import Modes.Mode;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;

public class GotYa extends Mode {
    private Circle circle;
    public GotYa(Canvas canvas) {
        super(canvas);

        circle = new Circle(canvasX / 2, canvasY / 2, 30, 0,
                new RadialGradient(0.63, 0.58, 0.7, 0.7,
                0.63, true, CycleMethod.NO_CYCLE, stops));

        Runnable moveAndDrawCircle = (() -> moveAndDrawCircle());
        executor1.submit(moveAndDrawCircle);
    }

    private void moveAndDrawCircle(){
        while(alive.get()){
            synchronized (circle) {
                graphicsContext.clearRect(0, 0, canvasX, canvasY);
                graphicsContext.setFill(circle.getColor());
                graphicsContext.fillOval(circle.getX() - circle.getR() / 2, circle.getY() - circle.getR() / 2,
                        circle.getR(), circle.getR());
                circle.setX(circle.getX() + 1);
            }
            try{
                Thread.sleep(10);
            }catch(InterruptedException e){}
        }
    }

    public void checkIfInside(double x, double y){
        synchronized (circle) {
            if (Math.sqrt(Math.pow((circle.getX() - x), 2) + Math.pow((circle.getY() - y), 2)) <= circle.getR() / 2) {
                circle.setColor(new RadialGradient(0.63, 0.58, 0.7, 0.7,
                        0.63, true, CycleMethod.NO_CYCLE, stops2));
            }
            else{
                circle.setColor(new RadialGradient(0.63, 0.58, 0.7, 0.7,
                        0.63, true, CycleMethod.NO_CYCLE, stops));
            }
        }
    }


}
