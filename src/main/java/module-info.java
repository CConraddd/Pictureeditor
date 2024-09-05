module com.example.labb4fix2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires javafx.swing;

    opens com.example.labb4fix2 to javafx.fxml;
    exports com.example.labb4fix2.View to javafx.graphics;
}