package ec.edu.utpl.carreras.computacion.domain.port;

import ec.edu.utpl.carreras.computacion.domain.Timer;

/**
 * Puerto: Listener para recibir eventos de cambio de tiempo de Timer.
 */
public interface TimerListener {
    /**
     * Invocado en cada tick del temporizador.
     * @param timer Temporizador que emitió el evento.
     */
    void onTick(Timer timer);
}