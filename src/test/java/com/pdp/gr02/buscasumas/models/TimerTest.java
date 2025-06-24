package com.pdp.gr02.buscasumas.models;

import static org.junit.jupiter.api.Assertions.*;

class TimerTest {
    // Aquí verificamos que el temporizador se inicializa correctamente  y
    // que cuenta el tiempo de forma adecuada, etc.

    @org.junit.jupiter.api.Test
    void testTimerInitialization() {
        Timer timer = new Timer();
        assertNotNull(timer);
    }

}