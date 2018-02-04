package main;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ReadingModeController {
    @FXML
    VBox vBox;

    @FXML
    public void initialize(){
        GridPane gridPane = (GridPane) vBox.getChildren().get(1);
        //gridPane.a
    }

}
