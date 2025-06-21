package com.pdp.gr02.buscasumas.views;

import com.pdp.gr02.buscasumas.controllers.MainAppController;
import com.pdp.gr02.buscasumas.models.Board;
import com.pdp.gr02.buscasumas.utils.ImageAssets;

import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Clase que representa la vista principal de la aplicación.
 * Se encarga de mostrar el estado del juego y de interactuar con el usuario.
 */
public class MainAppView {

    /**
     * Modelo del tablero de juego.
     * Este modelo contiene la información sobre el estado del juego, como las minas
     * y los botones.
     */
    private Board board;

    private Button[][] buttons;

    /**
     * Controlador de la aplicación.
     * Este controlador se encarga de manejar la lógica del juego y las
     * interacciones del usuario.
     */
    private MainAppController controller;

    /**
     * Contenedor principal de la vista.
     * Este contenedor se utiliza para organizar los elementos de la interfaz
     * gráfica.
     */
    private final BorderPane root = new BorderPane();

    /**
     * Constructor de la vista principal.
     * Inicializa el modelo del tablero y el controlador, y dibuja la vista.
     *
     * @param board      El modelo del tablero de juego.
     * @param controller El controlador de la aplicación.
     */
    public MainAppView(Board board, MainAppController controller) {
        this.board = board;
        this.controller = controller;

        DrawView();
    }

    /* Pinta los contenedores principales de la interfaz */
    private void DrawView() {
        root.setTop(CreateTopMenu(board.GetTotalMines()));
        root.setCenter(CreateBoard(this.board.GetRows(), this.board.GetColumns()));
    }

    /**
     * Crea el menú superior de la interfaz.
     * Este menú muestra información como el total de minas, un botón de carita
     * feliz y un temporizador.
     *
     * @param totalMines El número total de minas en el juego.
     * @return Un GridPane que contiene el menú superior.
     */
    private GridPane CreateTopMenu(int totalMines) {

        GridPane topContainer = new GridPane();

        topContainer.setMaxWidth(Double.MAX_VALUE);

        topContainer.paddingProperty().setValue(new javafx.geometry.Insets(10, 10, 10, 10));

        for (int i = 0; i < 3; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / 3); // 33.33% del ancho total
            cc.setHalignment(HPos.CENTER); // Centrar cada celda horizontalmente
            cc.setHgrow(Priority.ALWAYS); // Permitir que crezca proporcionalmente
            topContainer.getColumnConstraints().add(cc);
        }

        // Sección 1: Total de minas

        Label flagsLabel = new Label(String.format("%03d", totalMines));
        flagsLabel.setStyle("-fx-font-weight: bold;");

        topContainer.add(flagsLabel, 0, 0);

        // Sección 2: Botón de reinicio

        Button resetButton = new Button();

        resetButton.setPrefSize(20, 20);

        resetButton.setFocusTraversable(false);

        resetButton.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                controller.ResetGame();
            }
        });

        Image img = ImageAssets.GetRestartImage();

        ImageView imgView = new ImageView(img);

        imgView.setFitWidth(resetButton.getPrefWidth());
        imgView.setFitHeight(resetButton.getPrefHeight());
        imgView.setPreserveRatio(false);

        resetButton.setGraphic(imgView);

        topContainer.add(resetButton, 1, 0);

        // Sección 3: Temporizador

        Label timerLabel = new Label("10:00");
        timerLabel.setStyle("-fx-font-weight: bold;");

        topContainer.add(timerLabel, 2, 0);

        return topContainer;
    }

    /**
     * Crea el tablero de juego dependiendo del modelo proporcionado (estado
     * inicial)
     *
     * @param rows El número de filas del tablero.
     * @param cols El número de columnas del tablero.
     * @return Un GridPane que representa el tablero de juego.
     */
    private GridPane CreateBoard(int rows, int cols) {

        GridPane boardGrid = new GridPane();

        /* Limpia la grilla inicialmente */
        boardGrid.getChildren().clear();

        buttons = new Button[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                Button btn = new Button();

                btn.setPrefSize(30, 30);

                final int ri = i, cj = j;

                btn.setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        controller.HandleLeftClick(ri, cj);
                    } else if (e.getButton() == MouseButton.SECONDARY) {
                        controller.HandleRightClick(ri, cj);
                    }
                });

                btn.setFocusTraversable(false);

                ImageView imgView = new ImageView(ImageAssets.GetHiddenImage());

                imgView.setFitWidth(btn.getPrefWidth());
                imgView.setFitHeight(btn.getPrefHeight());
                btn.setGraphic(imgView);

                boardGrid.add(btn, j, i);

                buttons[i][j] = btn;
            }
        }

        controller.SetButtons(buttons);

        return boardGrid;
    }

    /**
     * Obtiene el contenedor raíz de la vista.
     * Este método es utilizado por la aplicación principal para agregar la vista a
     * la escena.
     *
     * @return El contenedor raíz de la vista.
     */
    public Parent getRoot() {
        return root;
    }
}
