module com.pdp.gr02.buscasumas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.pdp.gr02.buscasumas to javafx.fxml;
    exports com.pdp.gr02.buscasumas;
    exports com.pdp.gr02.buscasumas.controllers;
    opens com.pdp.gr02.buscasumas.controllers to javafx.fxml;
}