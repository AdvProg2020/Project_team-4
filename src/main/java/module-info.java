module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens org.example to javafx.fxml;
    opens Control to javafx.fxml;
    opens Model to javafx.fxml, com.google.gson;
    opens View to javafx.fxml;
    exports org.example;
}