package ec.edu.utpl.carreras.computacion.controller;


import ec.edu.utpl.carreras.computacion.domain.Timer;
import ec.edu.utpl.carreras.computacion.adapter.outbound.TimerAdapter;
import ec.edu.utpl.carreras.computacion.usecase.StartAllTimersUseCase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MultiTimerController implements Initializable {
    @FXML private VBox root;
    @FXML private HBox timerContainer;
    @FXML private Button startButton;

    private final List<TimerAdapter> adapters = new ArrayList<>();
    private StartAllTimersUseCase startAllTimersUseCase;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Timer> timers = List.of(
            new Timer("Temporizador A", 5),
            new Timer("Temporizador B", 8),
            new Timer("Temporizador C", 6)
        );

        for (Timer timer : timers) {
            TimerAdapter adapter = new TimerAdapter(timer);
            adapters.add(adapter);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TimerItem.fxml"));
            try {
                HBox itemRoot = loader.load();
                TimerItemController itemCtrl = loader.getController();
                itemCtrl.setAdapter(adapter);
                timerContainer.getChildren().add(itemRoot);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        startAllTimersUseCase = new StartAllTimersUseCase(timers);
    }



    @FXML
    private void onStartAll() {
        startAllTimersUseCase.execute();
    }



}
