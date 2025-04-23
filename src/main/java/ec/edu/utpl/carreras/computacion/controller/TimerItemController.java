package ec.edu.utpl.carreras.computacion.controller;

import ec.edu.utpl.carreras.computacion.model.TimerModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TimerItemController {
    @FXML private Label nameLabel;
    @FXML private Label timeLabel;

    public void setModel(TimerModel model) {
        nameLabel.setText(model.getName());
        timeLabel.textProperty().bind(model.secondsLeftProperty().asString().concat(" s"));
    }
}