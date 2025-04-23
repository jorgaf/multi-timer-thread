package ec.edu.utpl.carreras.computacion.controller;


import ec.edu.utpl.carreras.computacion.model.TimerModel;
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

    private final List<TimerModel> models = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        models.add(new TimerModel("Temporizador A", 5));
        models.add(new TimerModel("Temporizador B", 8));
        models.add(new TimerModel("Temporizador C", 3));

        for(var model : models) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TimerItem.fxml"));
            try {
                HBox itemRoot = loader.load();
                TimerItemController itemCtrl = loader.getController();
                itemCtrl.setModel(model);
                timerContainer.getChildren().add(itemRoot);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    @FXML
    private void onStartAll() {

        models.forEach(TimerModel::reset);

        models.stream().map(tm -> new Thread(tm, tm.getName())).forEach(thread -> {
            thread.setDaemon(true);
            thread.start();
        });

        //startButton.setDisable(true);
    }



}
