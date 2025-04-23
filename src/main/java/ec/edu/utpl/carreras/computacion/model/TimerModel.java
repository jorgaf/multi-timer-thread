package ec.edu.utpl.carreras.computacion.model;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;

public class TimerModel implements Runnable {
    private final String name;
    private final int initialSeconds;
    private final ReadOnlyIntegerWrapper secondsLeft = new ReadOnlyIntegerWrapper();

    public TimerModel(String name, int initialSeconds) {
        this.name = name;
        this.initialSeconds = initialSeconds;
        this.secondsLeft.set(initialSeconds);
    }

    public String getName() {
        return name;
    }

    public IntegerProperty secondsLeftProperty() {
        return secondsLeft;
    }

    public void reset() {
        Platform.runLater(() -> secondsLeft.set(initialSeconds));
    }

    @Override
    public void run() {
        var seconds = initialSeconds;
        while (seconds > 0) {
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            seconds--;
            int display = seconds;
            Platform.runLater(() -> secondsLeft.set(display));
        }
    }
}
