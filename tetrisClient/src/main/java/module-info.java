module com.example.tetrisclient {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.tetrisclient to javafx.fxml;
    exports com.example.tetrisclient;
}