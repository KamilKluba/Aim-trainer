package Modes.Precision;

import Data.Circle;
import Modes.Mode;
import controllers.PlayWindowController;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;

public class AnotherOneBitesTheDust extends Mode {
    private Circle circle;
    private int hitCircles = 0;

    public AnotherOneBitesTheDust(PlayWindowController playWindowController, double circleSize) {
        super(playWindowController);

        circle = new Circle(canvasX / 2, canvasY / 2, circleSize, -1,
                new RadialGradient(0.63, 0.58, 0.7, 0.7,
                0.63, true, CycleMethod.NO_CYCLE, stops));

        graphicsContext.setFill(circle.getColor());
        graphicsContext.fillOval(circle.getX() - circle.getR() / 2, circle.getY() - circle.getR() / 2, circle.getR(), circle.getR());
    }

    public void checkIfHit(double x, double y) {
        if (Math.sqrt(Math.pow((circle.getX() - x), 2) + Math.pow((circle.getY() - y), 2)) <= circle.getR() / 2) {
            circle.setX(random.nextInt(canvasX - 60) + 30);
            circle.setY(random.nextInt(canvasY - 60) + 30);
            graphicsContext.clearRect(0, 0, canvasX, canvasY);
            graphicsContext.fillOval(circle.getX() - circle.getR() / 2, circle.getY() - circle.getR() / 2, circle.getR(), circle.getR());
            hitCircles++;
            Platform.runLater(() -> playWindowController.getLabelResult1Value().setText("" + hitCircles));
        }
    }
}
