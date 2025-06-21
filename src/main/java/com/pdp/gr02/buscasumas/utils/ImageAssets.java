package com.pdp.gr02.buscasumas.utils;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class ImageAssets {

    private static final Map<Integer, Image> numberImages = new HashMap<>();

    private static Image restartImage;
    private static Image emptyImage;
    private static Image hiddenImage;
    private static Image flagImage;
    private static Image mineImage;

    static {
        // Cargar imágenes para los números (1 a 8)
        for (int i = 1; i <= 8; i++) {
            String path = "/com/pdp/gr02/buscasumas/assets/" + i + ".png";
            numberImages.put(i, new Image(ImageAssets.class.getResourceAsStream(path)));
        }
        restartImage =new Image(ImageAssets.class.getResourceAsStream(
                "/com/pdp/gr02/buscasumas/assets/restart.png"));

        emptyImage = new Image(ImageAssets.class.getResourceAsStream(
                "/com/pdp/gr02/buscasumas/assets/empty.png"));

        hiddenImage = new Image(ImageAssets.class.getResourceAsStream(
                "/com/pdp/gr02/buscasumas/assets/hidden.png"));

        flagImage = new Image(ImageAssets.class.getResourceAsStream(
                "/com/pdp/gr02/buscasumas/assets/flag.png"));
        mineImage = new Image(ImageAssets.class.getResourceAsStream(
                "/com/pdp/gr02/buscasumas/assets/mine.png"));
    }

    /** 
     * Obtiene la imagen correspondiente a un número específico.
     * @param number El número para el cual se desea obtener la imagen.
     * @return Image 
     */
    public static Image GetNumberImage(int number) {
        return numberImages.getOrDefault(number, null);
    }

        /** 
     * Obtiene la imagen de un botón vacío.
     * @return Image
     */
    public static Image GetRestartImage() {
        return restartImage;
    }

    /** 
     * Obtiene la imagen de un botón vacío.
     * @return Image
     */
    public static Image GetEmptyImage() {
        return emptyImage;
    }

    /** 
     * Obtiene la imagen de un botón oculto.
     * @return Image
     */
    public static Image GetHiddenImage() {
        return hiddenImage;
    }

    /** 
     * Obtiene la imagen de una bandera.
     * @return Image
     */
    public static Image GetFlagImage() {
        return flagImage;
    }

    /** 
     * Obtiene la imagen de una mina.
     * @return Image
     */
    public static Image GetMineImage() {
        return mineImage;
    }
}