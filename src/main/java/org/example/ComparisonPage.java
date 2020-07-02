package org.example;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class ComparisonPage {
    public HBox comparisonPageHBox = new HBox();
    public static ArrayList<VBox> vBoxes = new ArrayList<>();

    public static void setVBox(VBox vBox) {
        vBoxes.add(vBox);
    }

    public Button back;

    public void setComparisonPageHBox() {
        comparisonPageHBox.getChildren().addAll(vBoxes);
    }

    public static ArrayList<VBox> getvBoxes() {
        return vBoxes;
    }

    public void back(ActionEvent actionEvent) throws IOException {
        App.setRoot("product-page");
    }

    public void initialize(){
        setComparisonPageHBox();
    }
}
