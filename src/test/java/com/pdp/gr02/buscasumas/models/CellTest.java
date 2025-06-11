package com.pdp.gr02.buscasumas.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test

    void testSetMine() {
        Cell cell = new Cell(false, true, false, 0);
        cell.SetMine(true);

        assertTrue(cell.HasMine(), "Cell should now have a mine");
    }

    @Test
    void testSetHidden() {
        Cell cell = new Cell(false, false, false, 0);
        cell.SetHidden(true);

        assertTrue(cell.IsHidden(), "Cell should now be hidden");
    }

}