package com.pdp.gr02.buscasumas.models;

import static org.junit.jupiter.api.Assertions.*;

class MusicTest {

    // Aquí verificamos que los clips de audio se cargan correctamente,
    // que la música de fondo se reproduce y se detiene, etc.

    @org.junit.jupiter.api.Test
    void testMusicInitialization() {
        Music music = new Music();
        assertNotNull(music);
        assertDoesNotThrow(music::playBackgroundMusic);
        assertDoesNotThrow(music::stopBackgroundMusic);
    }

}