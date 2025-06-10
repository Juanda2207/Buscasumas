package com.pdp.gr02.buscasumas.models;

import java.util.Random;

public class Board {

    private Cell[][] cells;
    private int rows, cols, totalMines;

    private void CreateBoard(){

        /* Se agregan todas celdas sin minas */
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.cells[i][j] = new Cell(false, true, false, 0);
            }
        }

        /* Se agrega el total de minas a celdas específicas de manera aleatoria */
        Random rand = new Random();
        int placed = 0;
        while (placed < totalMines) {
            int r = rand.nextInt(rows);
            int c = rand.nextInt(cols);

            boolean hasMine = cells[r][c].HasMine();

            if(!hasMine){
                cells[r][c].SetMine(true);
                placed++;
            }
        }

        /* Se calculan las minas adyacentes después de haber sido agregadas */
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int count = 0;
                // recorre las 8 direcciones alrededor de (i,j)
                for (int di = -1; di <= 1; di++) {
                    for (int dj = -1; dj <= 1; dj++) {
                        int ni = i + di, nj = j + dj;
                        // saltar fuera de límites y la propia celda
                        if ((di != 0 || dj != 0)
                                && ni >= 0 && ni < rows
                                && nj >= 0 && nj < cols
                                && cells[ni][nj].HasMine()) {
                            count++;
                        }
                    }
                }
                cells[i][j].SetAdjacentMines(count);
            }
        }
    }

    public Board(int rows, int cols, int totalMines){
        this.rows = rows;
        this.cols = cols;
        this.totalMines = totalMines;

        this.cells = new Cell[rows][cols];

        CreateBoard();
    }

    public Cell GetCell(int i, int j){
        return cells[i][j];
    }

    public int GetRows(){
        return this.rows;
    }

    public int GetColumns(){
        return this.cols;
    }

    public int GetTotalMines(){
        return this.totalMines;
    }
}
