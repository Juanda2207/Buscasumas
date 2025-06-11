package com.pdp.gr02.buscasumas.controllers;

import com.pdp.gr02.buscasumas.models.Board;
import com.pdp.gr02.buscasumas.models.Cell;
import javafx.scene.control.Button;

/**
 * Clase que representa el controlador principal de la aplicación.
 * Se encarga de manejar las interacciones del usuario con el tablero de juego.
 */
public class MainAppController {
    /**
     * Modelo del tablero de juego.
     * Este modelo contiene la información sobre el estado del juego, como las minas y los botones.
     */
    private Board board;

    /**
     * Constructor del controlador principal.
     * Inicializa el modelo del tablero de juego.
     *
     * @param board El modelo del tablero de juego.
     */
    public MainAppController(Board board){
        this.board = board;
    }


    /**
     * Maneja el evento de clic derecho en una celda del tablero.
     * Si la celda contiene una mina, se muestra un mensaje en el botón correspondiente.
     *
     * @param i La fila de la celda.
     * @param j La columna de la celda.
     * @param btn El botón asociado a la celda.
     */
    public void HandleLeftClick(int i, int j, Button btn){

        Cell cell = board.GetCell(i, j);

        if(cell.HasMine()){
            btn.setText("B");
        } else {
            btn.setText(String.valueOf(cell.GetAdjacentMines()));
        }
    }
}