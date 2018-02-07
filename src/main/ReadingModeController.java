package main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ReadingModeController {
    @FXML
    VBox vBox;

    @FXML
    public void initialize(){
        GridPane gridPane = (GridPane) vBox.getChildren().get(1);
        gridPane.addRow(1,new Button());
        gridPane.add(new Button(), 1,1);
    }

}
