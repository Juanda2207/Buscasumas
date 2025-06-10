package com.pdp.gr02.buscasumas.models;

import java.util.Random;

/**
 * Clase que representa el tablero del juego Buscasumas.
 * Contiene una matriz de celdas y métodos para crear el tablero,
 * obtener celdas y obtener información sobre el tablero.
 */
public class Board {

    /**
     * Matriz de celdas que componen el tablero.
     * Cada celda puede contener una mina, estar oculta, marcada con una bandera
     * y tener un número de minas adyacentes.
     */
    private Cell[][] cells;
    private int rows, cols, totalMines; // Número total de filas, columnas y minas en el tablero

    /**
     * Crea el tablero de juego inicializando las celdas y colocando minas aleatoriamente.
     * También calcula el número de minas adyacentes para cada celda.
     */
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

    /**
     * Constructor de la clase Board.
     * Inicializa el tablero con el número de filas, columnas y minas especificadas.
     *
     * @param rows Número de filas del tablero.
     * @param cols Número de columnas del tablero.
     * @param totalMines Número total de minas en el tablero.
     */
    public Board(int rows, int cols, int totalMines){
        this.rows = rows;
        this.cols = cols;
        this.totalMines = totalMines;

        this.cells = new Cell[rows][cols];

        CreateBoard();
    }

    /**
     * Obtiene una celda del tablero.
     *
     * @param i La fila de la celda.
     * @param j La columna de la celda.
     * @return La celda en la posición (i, j).
     */
    public Cell GetCell(int i, int j){
        return cells[i][j];
    }

    /**
     * Obtiene una celda del tablero.
     *
     * @param i La fila de la celda.
     * @param j La columna de la celda.
     * @return La celda en la posición (i, j).
     */
    public int GetRows(){
        return this.rows;
    }

    /**
     * Obtiene el número de columnas del tablero.
     *
     * @return El número de columnas del tablero.
     */
    public int GetColumns(){
        return this.cols;
    }

    /**
     * Obtiene el número total de minas en el tablero.
     *
     * @return El número total de minas en el tablero.
     */
    public int GetTotalMines(){
        return this.totalMines;
    }
}
