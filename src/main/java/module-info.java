module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.media;

    opens org.example to javafx.fxml;
    opens Control to javafx.fxml;
    opens Model to javafx.fxml, com.google.gson, javafx.base;
    exports org.example;
    exports Model;
    exports Control;
}