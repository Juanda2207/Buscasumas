package com.pdp.gr02.buscasumas.models;

/**
 * Clase que representa una celda en el tablero del juego Buscasumas.
 * Cada celda puede contener una mina, estar oculta, marcada con una bandera
 * y tener un número de minas adyacentes.
 */
public class Cell {


    private boolean hasMine; // Indica si la celda contiene una mina
    private boolean hidden; // Indica si la celda está oculta
    private boolean flagged; // Indica si la celda está marcada con una bandera
    private int adjacentMines; // Número de minas adyacentes a esta celda
    
    /**
     * Constructor de la clase Cell.
     * Inicializa una celda con los valores proporcionados.
     *
     * @param hasMine Indica si la celda contiene una mina.
     * @param hidden Indica si la celda está oculta.
     * @param flagged Indica si la celda está marcada con una bandera.
     * @param adjacentMines Número de minas adyacentes a esta celda.
     */
    public Cell(boolean hasMine, boolean hidden, boolean flagged, int adjacentMines){
        this.hasMine = hasMine;
        this.hidden = hidden;
        this.flagged = flagged;
        this.adjacentMines = 0;
    }

    public boolean HasMine(){
        return this.hasMine;
    }

    /**
     * Establece si la celda contiene una mina.
     *
     * @param hasMine Indica si la celda debe contener una mina.
     */
    public void SetMine(boolean hasMine){
        this.hasMine = hasMine;
    }

    /**
     * Verifica si la celda está oculta.
     *
     * @return true si la celda está oculta, false en caso contrario.
     */
    public boolean IsHidden(){
        return this.hidden;
    }

    /**
     * Establece el estado de ocultación de la celda.
     *
     * @param hidden Indica si la celda debe estar oculta.
     */
    public void SetHidden(boolean hidden){
        this.hidden = hidden;
    }

    /**
     * Verifica si la celda está marcada con una bandera.
     *
     * @return true si la celda está marcada, false en caso contrario.
     */
    public boolean IsFlagged(){
        return this.flagged;
    }

    /**
     * Establece el estado de la bandera de la celda.
     *
     * @param flagged Indica si la celda debe estar marcada con una bandera.
     */
    public void SetFlagged(boolean flagged){
        this.flagged = flagged;
    }

    /**
     * Obtiene el número de minas adyacentes a esta celda.
     *
     * @return El número de minas adyacentes.
     */
    public int GetAdjacentMines(){
        return this.adjacentMines;
    }

    /**
     * Establece el número de minas adyacentes a esta celda.
     *
     * @param adjacentMines El número de minas adyacentes a establecer.
     */
    public void SetAdjacentMines(int adjacentMines){
        this.adjacentMines = adjacentMines;
    }
}
