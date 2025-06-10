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

public class MainAppView {

    private Board board;
    private MainAppController controller;

    private final BorderPane root = new BorderPane();

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

    /** Crea el tablero dependiendo del modelo proporcionado */
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

    /** Para que MainApp lo añada a la Scene */
    public Parent getRoot() {
        return root;
    }
}
