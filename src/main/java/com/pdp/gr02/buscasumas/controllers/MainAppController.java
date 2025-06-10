package com.pdp.gr02.buscasumas.controllers;

import com.pdp.gr02.buscasumas.models.Board;
import com.pdp.gr02.buscasumas.models.Cell;
import javafx.scene.control.Button;

public class MainAppController {

    private Board board;

    public MainAppController(Board board){
        this.board = board;
    }

    /* Cuando el usuario hace clic derecho para revelar una mina */
    public void HandleLeftClick(int i, int j, Button btn){

        Cell cell = board.GetCell(i, j);

        if(cell.HasMine()){
            btn.setText("B");
        }else{
            btn.setText(String.valueOf(cell.GetAdjacentMines()));
        }
    }
}