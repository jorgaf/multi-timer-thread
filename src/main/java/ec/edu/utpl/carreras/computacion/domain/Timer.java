package ec.edu.utpl.carreras.computacion.domain;

import ec.edu.utpl.carreras.computacion.domain.port.TimerListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Dominio: Entidad Timer pura, sin dependencias de framework.
 */
public class Timer {
    private final String name;
    private final int initialSeconds;
    private int secondsLeft;
    private final List<TimerListener> listeners = new ArrayList<>();

    public Timer(String name, int initialSeconds) {
        this.name = name;
        this.initialSeconds = initialSeconds;
        this.secondsLeft = initialSeconds;
    }

    public String getName() {
        return name;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }

    /**
     * Resetea el temporizador al valor inicial y notifica a los listeners.
     */
    public void reset() {
        this.secondsLeft = initialSeconds;
        notifyListeners();
    }

    /**
     * Inicia la cuenta regresiva en el hilo actual.
     */
    public void start() {
        int seconds = initialSeconds;
        while (seconds > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            seconds--;
            this.secondsLeft = seconds;
            notifyListeners();
        }
    }

    public void addListener(TimerListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (TimerListener listener : listeners) {
            listener.onTick(this);
        }
    }
}