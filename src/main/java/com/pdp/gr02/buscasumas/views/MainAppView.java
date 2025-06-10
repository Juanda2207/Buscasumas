package com.pdp.gr02.buscasumas.views;

import com.pdp.gr02.buscasumas.controllers.MainAppController;
import com.pdp.gr02.buscasumas.models.Board;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
     * Este modelo contiene la información sobre el estado del juego, como las minas y los botones.
     */
    private Board board;
    /**
     * Controlador de la aplicación.
     * Este controlador se encarga de manejar la lógica del juego y las interacciones del usuario.
     */
    private MainAppController controller;

    /**
     * Contenedor principal de la vista.
     * Este contenedor se utiliza para organizar los elementos de la interfaz gráfica.
     */
    private final BorderPane root = new BorderPane();

    /**
     * Constructor de la vista principal.
     * Inicializa el modelo del tablero y el controlador, y dibuja la vista.
     *
     * @param board El modelo del tablero de juego.
     * @param controller El controlador de la aplicación.
     */
    public MainAppView(Board board, MainAppController controller){
        this.board = board;
        this.controller = controller;

        DrawView();
    }

    /* Pinta los contenedores principales de la interfaz */
    private void DrawView(){
        root.setTop(CreateTopMenu(board.GetTotalMines()));
        root.setCenter(CreateBoard(this.board.GetRows(), this.board.GetColumns()));
    }

    /**
     * Crea el menú superior de la interfaz.
     * Este menú muestra información como el total de minas, un botón de carita feliz y un temporizador.
     *
     * @param totalMines El número total de minas en el juego.
     * @return Un GridPane que contiene el menú superior.
     */
    private GridPane CreateTopMenu(int totalMines){

        GridPane topContainer = new GridPane();

        topContainer.setMaxWidth(Double.MAX_VALUE);

        // 2. Definir 3 columnas iguales (33.33% cada una)
        for (int i = 0; i < 3; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / 3);          // 33.33% del ancho total
            cc.setHalignment(HPos.CENTER);          // Centrar cada celda horizontalmente
            cc.setHgrow(Priority.ALWAYS);           // Permitir que crezca proporcionalmente
            topContainer.getColumnConstraints().add(cc);
        }

        topContainer.add(new Label("Total minas: " + totalMines), 0, 0);
        topContainer.add(new Label("Carita feliz"), 1, 0);
        topContainer.add(new Label("Timer: 10:00"), 2, 0);

        return topContainer;
    }

    /**
     * Crea el tablero de juego dependiendo del modelo proporcionado
     *
     * @param rows El número de filas del tablero.
     * @param cols El número de columnas del tablero.
     * @return Un GridPane que representa el tablero de juego.
     */
    private GridPane CreateBoard(int rows, int cols) {

        GridPane boardGrid = new GridPane();

        /* Limpia la grilla inicialmente */
        boardGrid.getChildren().clear();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Button btn = new Button();
                btn.setPrefSize(30, 30);
                final int ri = i, cj = j;
                btn.setOnAction(e -> controller.HandleLeftClick(ri, cj, btn));
                boardGrid.add(btn, j, i);
            }
        }
        return boardGrid;
    }

    /**
     * Obtiene el contenedor raíz de la vista.
     * Este método es utilizado por la aplicación principal para agregar la vista a la escena.
     *
     * @return El contenedor raíz de la vista.
     */
    public Parent getRoot() {
        return root;
    }
}
