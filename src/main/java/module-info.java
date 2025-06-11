module com.pdp.gr02.buscasumas {
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens com.pdp.gr02.buscasumas to javafx.fxml;
    exports com.pdp.gr02.buscasumas;
    exports com.pdp.gr02.buscasumas.controllers;
    exports com.pdp.gr02.buscasumas.models;
    
    opens com.pdp.gr02.buscasumas.controllers to javafx.fxml;
}