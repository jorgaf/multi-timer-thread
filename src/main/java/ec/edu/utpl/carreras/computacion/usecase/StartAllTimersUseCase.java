package ec.edu.utpl.carreras.computacion.usecase;

import ec.edu.utpl.carreras.computacion.domain.Timer;
import java.util.List;

/**
 * Caso de uso: Inicia todos los temporizadores.
 */
public class StartAllTimersUseCase {
    private final List<Timer> timers;

    public StartAllTimersUseCase(List<Timer> timers) {
        this.timers = timers;
    }

    /**
     * Ejecuta el caso de uso: resetea y arranca cada temporizador en un hilo daemon.
     */
    public void execute() {
        timers.forEach(Timer::reset);
        timers.stream()
            .map(timer -> new Thread(timer::start, timer.getName()))
            .forEach(thread -> {
                thread.setDaemon(true);
                thread.start();
            });
    }
}