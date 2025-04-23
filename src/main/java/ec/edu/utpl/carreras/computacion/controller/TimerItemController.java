package ec.edu.utpl.carreras.computacion.controller;

import ec.edu.utpl.carreras.computacion.adapter.outbound.TimerAdapter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TimerItemController {
    @FXML private Label nameLabel;
    @FXML private Label timeLabel;

    /**
     * Configura el adaptador de temporizador y vincula a la UI.
     */
    public void setAdapter(TimerAdapter adapter) {
        nameLabel.setText(adapter.getTimer().getName());
        timeLabel.textProperty().bind(adapter.secondsLeftProperty().asString().concat(" s"));
    }
}