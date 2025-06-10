package com.pdp.gr02.buscasumas.models;

public class Cell {

    private boolean hasMine;
    private boolean hidden;
    private boolean flagged;
    private int adjacentMines;

    public Cell(boolean hasMine, boolean hidden, boolean flagged, int adjacentMines){
        this.hasMine = hasMine;
        this.hidden = hidden;
        this.flagged = flagged;
        this.adjacentMines = 0;
    }

    public boolean HasMine(){
        return this.hasMine;
    }

    public void SetMine(boolean hasMine){
        this.hasMine = hasMine;
    }

    public boolean IsHidden(){
        return this.hidden;
    }

    public void SetHidden(boolean hidden){
        this.hidden = hidden;
    }

    public boolean IsFlagged(){
        return this.flagged;
    }

    public void SetFlagged(boolean flagged){
        this.flagged = flagged;
    }

    public int GetAdjacentMines(){
        return this.adjacentMines;
    }

    public void SetAdjacentMines(int adjacentMines){
        this.adjacentMines = adjacentMines;
    }
}
