package main.bancaria.gui;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Toast {

    public static void show(StackPane root, String message) {
        Label toast = new Label(message);
        toast.setStyle(
                "-fx-background-radius: 10; " +
                        "-fx-background-color: rgba(0,0,0,0.7); " +
                        "-fx-text-fill: white; " +
                        "-fx-padding: 10;"
        );
        toast.setOpacity(0);

        root.getChildren().add(toast);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), toast);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        PauseTransition stay = new PauseTransition(Duration.seconds(2));

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), toast);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> root.getChildren().remove(toast));

        fadeIn.setOnFinished(e -> stay.play());
        stay.setOnFinished(e -> fadeOut.play());

        fadeIn.play();
    }
}
