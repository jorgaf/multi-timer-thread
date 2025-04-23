package ec.edu.utpl.carreras.computacion.adapter.outbound;

import ec.edu.utpl.carreras.computacion.domain.Timer;
import ec.edu.utpl.carreras.computacion.domain.port.TimerListener;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyIntegerProperty;

/**
 * Adaptador de Timer para JavaFX: convierte eventos de dominio en propiedades observables.
 */
public class TimerAdapter implements TimerListener {
    private final Timer timer;
    private final ReadOnlyIntegerWrapper secondsLeftWrapper = new ReadOnlyIntegerWrapper();

    public TimerAdapter(Timer timer) {
        this.timer = timer;
        this.secondsLeftWrapper.set(timer.getSecondsLeft());
        timer.addListener(this);
    }

    public Timer getTimer() {
        return timer;
    }

    /**
     * Propiedad de solo lectura con los segundos restantes.
     */
    public ReadOnlyIntegerProperty secondsLeftProperty() {
        return secondsLeftWrapper.getReadOnlyProperty();
    }

    @Override
    public void onTick(Timer timer) {
        int seconds = timer.getSecondsLeft();
        Platform.runLater(() -> secondsLeftWrapper.set(seconds));
    }
}