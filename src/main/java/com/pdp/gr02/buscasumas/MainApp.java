package com.pdp.gr02.buscasumas;

import com.pdp.gr02.buscasumas.controllers.MainAppController;
import com.pdp.gr02.buscasumas.models.Board;
import com.pdp.gr02.buscasumas.views.MainAppView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private final String _app_name = "Buscasumas";

    @Override
    public void start(Stage stage) throws IOException {

        /* Se crea el modelo base */
        Board board = new Board(10, 10, 8);

        /* Se crea el controlador a partir del modelo base */
        MainAppController controller = new MainAppController(board);

        /* Se crea la vista a partir del modelo y el controlador */
        MainAppView view = new MainAppView(board, controller);

        /* Se agrega la escena principal del juego */
        Scene scene = new Scene(view.getRoot());
        stage.setTitle(_app_name);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}