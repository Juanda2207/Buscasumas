package com.pdp.gr02.buscasumas.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void testBoardInitialization() {
        Board board = new Board(5, 5, 5);

        assertNotNull(board, "Board should be initialized");
        assertEquals(5, board.GetRows(), "Board should have 5 rows");
        assertEquals(5, board.GetColumns(), "Board should have 5 columns");
        assertEquals(5, board.GetTotalMines(), "Board should have 5 mines");

    }
}