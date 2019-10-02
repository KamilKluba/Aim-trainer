package Modes.Precision;

import Data.Circle;
import Modes.Mode;
import controllers.PlayWindowController;
import javafx.application.Platform;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;

public class AnotherOneBitesTheDust extends Mode {
    private Circle circle;

    public AnotherOneBitesTheDust(PlayWindowController playWindowController, double circleSize) {
        super(playWindowController);

        circle = new Circle(canvasX / 2, canvasY / 2, circleSize, -1, stops0);

        circle.paint(graphicsContext);
    }

    public void checkIfHit(double x, double y) {
        if (Math.sqrt(Math.pow((circle.getX() - x), 2) + Math.pow((circle.getY() - y), 2)) <= circle.getR() / 2) {
            circle.setX(random.nextInt(canvasX - 60) + 30);
            circle.setY(random.nextInt(canvasY - 60) + 30);
            graphicsContext.clearRect(0, 0, canvasX + 1, canvasY + 1);
            circle.paint(graphicsContext);
            hitCircles++;
            Platform.runLater(() -> playWindowController.getLabelResult1Value().setText("" + hitCircles));
        }
        else{
            missedHits++;
            Platform.runLater(() -> playWindowController.getLabelResult2Value().setText("" + missedHits));
        }
    }
}
