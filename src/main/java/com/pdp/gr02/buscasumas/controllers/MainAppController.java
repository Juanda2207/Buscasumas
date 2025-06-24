package com.pdp.gr02.buscasumas.controllers;

import com.pdp.gr02.buscasumas.models.Board;
import com.pdp.gr02.buscasumas.models.Cell;
import com.pdp.gr02.buscasumas.models.Music;
import com.pdp.gr02.buscasumas.utils.ImageAssets;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
/**
 * Clase que representa el controlador principal de la aplicación.
 * Se encarga de manejar las interacciones del usuario con el tablero de juego.
 */
public class MainAppController {
    /**
     * Modelo del tablero de juego.
     * Este modelo contiene la información sobre el estado del juego, como las minas
     * y los botones.
     */
    private Board board;
    private Music music;
    private Button[][] buttons;

    private Timeline timer;
    private int secondsRemaining;
    private Label timerLabel;
    private int time;

    /**
     * Constructor del controlador principal.
     * Inicializa el modelo del tablero de juego.
     *
     * @param board El modelo del tablero de juego.
     */
    public MainAppController(Board board, int initialSeconds) {
        this.board = board;
        this.music = new Music();
        this.music.playBackgroundMusic();
        this.time = initialSeconds;
    }

    /**
     * @return Button[][]
     */
    public Button[][] GetButtons() {
        return buttons;
    }

    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void ShowTimeOverMessage() {
        music.stopBackgroundMusic();
        music.playErrorSound();

        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tiempo agotado");
            alert.setHeaderText(null);
            alert.setContentText("Se acabó el tiempo. ¡Intenta de nuevo!");
            alert.showAndWait();
        });
    }

    public void StartCountdown(Label label) {
        this.timerLabel = label;
        this.secondsRemaining = this.time;

        if (timer != null) timer.stop();

        timerLabel.setText(formatTime(secondsRemaining));

        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            secondsRemaining--;

            timerLabel.setText(formatTime(secondsRemaining));

            if (secondsRemaining == 20) {
                music.stopBackgroundMusic();
                music.playMissingTimeSound();
            }

            if (secondsRemaining <= 0) {
                timer.stop();
                ShowTimeOverMessage();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }


    public void StopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

    /**
     * @param buttons
     */
    public void SetButtons(Button[][] buttons) {
        this.buttons = buttons;
    }

    private void ShowVictoryMessage() {
        this.music.stopBackgroundMusic();
        StopTimer();
        this.music.playSuccessSound();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Felicidades!");
        alert.setHeaderText(null);
        alert.setContentText("Has ganado, encontraste todas las minas");
        alert.showAndWait();
    }

    private void ShowGameOverMessage() {
        this.music.stopBackgroundMusic();
        StopTimer();
        this.music.playErrorSound();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Juego terminado");
        alert.setHeaderText(null);
        alert.setContentText("Has perdido, tocaste una mina");
        alert.showAndWait();
    }

    /**
     * Configura la imagen de un botón con una imagen específica.
     * 
     * @param btn El botón al que se le asignará la imagen.
     * @param img La imagen que se asignará al botón.
     */
    private void SetImageButton(Button btn, Image img) {

        ImageView imgView = new ImageView(img);

        imgView.setFitWidth(btn.getPrefWidth() - 1);
        imgView.setFitHeight(btn.getPrefHeight() - 1);
        imgView.setPreserveRatio(false);

        btn.setGraphic(imgView);
    }

    /**
     * Implementación del algoritmo de Flood Fill
     * Este método se puede utilizar para revelar celdas adyacentes vacías cuando se
     * hace clic en una celda sin minas adyacentes.
     *
     * @param i La fila de la celda.
     * @param j La columna de la celda.
     */
    private void FloodFill(int i, int j) {

        // Verifica si la celda está dentro de los límites del tablero
        if (i < 0 || i >= board.GetRows() || j < 0 || j >= board.GetColumns()) {
            return;
        }

        Cell cell = board.GetCell(i, j);

        // Si la celda ya está revelada o tiene una mina, no hacer nada
        if (!cell.IsHidden() || cell.HasMine() || cell.IsFlagged()) {
            return;
        }

        Button btn = buttons[i][j];

        // Revela la celda
        cell.SetHidden(false);

        int adjacentMines = cell.GetAdjacentMines();

        Image img = adjacentMines == 0
                ? ImageAssets.GetEmptyImage()
                : ImageAssets.GetNumberImage(adjacentMines);

        SetImageButton(btn, img);

        btn.setDisable(true);

        CheckWinCondition();

        // Si la celda no tiene minas adyacentes, aplicar Flood Fill a las celdas
        // adyacentes
        if (cell.GetAdjacentMines() == 0) {
            FloodFill(i - 1, j - 1); // Arriba izquierda
            FloodFill(i - 1, j); // Arriba
            FloodFill(i - 1, j + 1); // Arriba derecha
            FloodFill(i, j - 1); // Izquierda
            FloodFill(i, j + 1); // Derecha
            FloodFill(i + 1, j - 1); // Abajo izquierda
            FloodFill(i + 1, j); // Abajo
            FloodFill(i + 1, j + 1); // Abajo derecha
        }
    }

    private void CheckWinCondition() {
        if (board.HasPlayerWon()) {

            // Deshabilitar todos los botones
            for (int row = 0; row < board.GetRows(); row++) {
                for (int col = 0; col < board.GetColumns(); col++) {

                    Button other_btn = buttons[row][col];

                    other_btn.setDisable(true);
                }
            }

            ShowVictoryMessage();
        }
    }

    /**
     * Implementa el manejo del clic en una celda que contiene una mina.
     * Muestra una imagen de mina en el botón correspondiente y deshabilita el
     * botón.
     * 
     * @param i La fila de la celda.
     * @param j La columna de la celda.
     */
    private void HandleMineClick(int i, int j) {

        // TODO: Condicionar con el modal con el desafio
        this.music.playAlertSound();

        Cell cell = board.GetCell(i, j);

        if (!cell.IsHidden() || cell.IsFlagged())
            return;

        cell.SetHidden(false);

        Button btn = buttons[i][j];

        Image img = ImageAssets.GetMineImage();

        SetImageButton(btn, img);

        btn.setDisable(true);

        // Revelar todas las demás minas del tablero
        for (int row = 0; row < board.GetRows(); row++) {
            for (int col = 0; col < board.GetColumns(); col++) {

                Cell otherCell = board.GetCell(row, col);

                Button other_btn = buttons[row][col];

                if (otherCell.HasMine() && otherCell.IsHidden()) {

                    SetImageButton(other_btn, img);

                    otherCell.SetHidden(false);
                }

                other_btn.setDisable(true);
            }
        }

        ShowGameOverMessage();
    }

    /**
     * Maneja el evento de clic derecho en una celda del tablero.
     * Si la celda contiene una mina, se muestra un mensaje en el botón
     * correspondiente.
     *
     * @param i La fila de la celda.
     * @param j La columna de la celda.
     */
    public void HandleLeftClick(int i, int j) {

        Cell cell = board.GetCell(i, j);

        if (!cell.IsHidden())
            return;

        if (cell.HasMine()) {
            HandleMineClick(i, j);
            return;
        }

        FloodFill(i, j);
    }

    /**
     * Maneja el evento de clic derecho en una celda del tablero, que permite marcar
     * o desmarcar la casilla con una bandera, con el fin de indicar que se sospecha
     * que contiene una mina.
     * 
     * @param i La fila de la celda.
     * @param j La columna de la celda.
     */
    public void HandleRightClick(int i, int j) {

        Cell cell = board.GetCell(i, j);

        if (!cell.IsHidden())
            return;

        Button btn = buttons[i][j];

        Image img = cell.IsFlagged()
                ? ImageAssets.GetHiddenImage()
                : ImageAssets.GetFlagImage();

        SetImageButton(btn, img);

        cell.SetFlagged(!cell.IsFlagged());

        CheckWinCondition();
    }


    public void ResetGame() {

        board.Reset();
        this.music.playBackgroundMusic();
        StartCountdown(timerLabel);

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                Button btn = buttons[i][j];
                SetImageButton(btn, ImageAssets.GetHiddenImage());
                btn.setDisable(false);
            }
        }
    }
}