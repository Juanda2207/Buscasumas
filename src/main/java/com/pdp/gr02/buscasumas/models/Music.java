package com.pdp.gr02.buscasumas.models;

import javafx.scene.media.AudioClip;

/**
 * Clase que contiene los métodos para la reproducción de música y efectos en la aplicación Buscasumas.
 */
public class Music {

    private final AudioClip successSound;
    private final AudioClip errorSound;
    private final AudioClip backgroundMusic;
    private final AudioClip alertSound;
    private final AudioClip lackOfTimeMusic;

    public Music() {
        successSound = loadClip("/com/pdp/gr02/buscasumas/assets/music/success.mp3");
        errorSound = loadClip("/com/pdp/gr02/buscasumas/assets/music/error.wav");
        backgroundMusic = loadClip("/com/pdp/gr02/buscasumas/assets/music/background.mp3");
        alertSound = loadClip("/com/pdp/gr02/buscasumas/assets/music/alert.wav");
        lackOfTimeMusic = loadClip("/com/pdp/gr02/buscasumas/assets/music/lackOfTime.wav");
    }

    private AudioClip loadClip(String path) {
        try {
            return new AudioClip(getClass().getResource(path).toExternalForm());
        } catch (Exception e) {
            System.err.println("Error al cargar el archivo de sonido: " + path);
            return null;
        }
    }

    // Música de fondo
    public void playBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.setCycleCount(AudioClip.INDEFINITE); // bucle infinito
            backgroundMusic.play();
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }

    // Sonido de éxito
    public void playSuccessSound() {
        if (successSound != null) {
            successSound.play();
        }
    }

    // Sonido de error
    public void playErrorSound() {
        if (errorSound != null) {
            errorSound.play();
        }
    }

    // Sonido de alerta
    public void playAlertSound() {
        if (alertSound != null) {
            alertSound.play();
        }
    }

    // Sonido de missingTime
    public void playMissingTimeSound() {
        if (lackOfTimeMusic != null) {
            lackOfTimeMusic.play();
        }
    }
}
