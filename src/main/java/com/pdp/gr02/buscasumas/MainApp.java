package com.pdp.gr02.buscasumas;

import com.pdp.gr02.buscasumas.controllers.MainAppController;
import com.pdp.gr02.buscasumas.models.Board;
import com.pdp.gr02.buscasumas.views.MainAppView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    /**
     * Nombre de la aplicación.
     * Este nombre se muestra en la ventana principal del juego.
     */
    private final String _app_name = "Buscasumas";

    /**
     * Método que se ejecuta al iniciar la aplicación.
     * Aquí se configura la ventana principal y se inicia el juego.
     *
     * @param stage La ventana principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar los recursos necesarios.
     */
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
        scene.getStylesheets().add(getClass().getResource("/com/pdp/gr02/buscasumas/css/style.css").toExternalForm());

        Image icon = new Image(getClass().getResourceAsStream("/com/pdp/gr02/buscasumas/assets/icon.png"));
        stage.getIcons().add(icon);

        stage.setTitle(_app_name);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método principal que inicia la aplicación.
     * Este método es el punto de entrada de la aplicación JavaFX.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        /* Se lanza la aplicación de JavaFX */
        launch();
    }
}