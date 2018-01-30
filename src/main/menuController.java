package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class menuController {

    public void training(ActionEvent event) throws IOException {
        switch (((Button)event.getSource()).getText()) {
            case "Hiragana":
                trainerController.setMode(Kana.HIRAGANA); break;
            case "Katakana":
                trainerController.setMode(Kana.KATAKANA); break;
            default:
                System.out.print("ERROR IN SETTING MODE");
                trainerController.setMode(Kana.HIRAGANA); break;
        }

        Stage trainingStage = new Stage();
        VBox vBox = FXMLLoader.load(getClass().getResource("trainer.fxml"));

        trainingStage.setTitle("Kana Trainer");
        trainingStage.setScene(new Scene(vBox, 700, 500));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        trainingStage.setMaxHeight(500);
        trainingStage.setMaxWidth(700);
        trainingStage.setMinWidth(460);
        trainingStage.setMinHeight(350);
        trainingStage.initModality(Modality.WINDOW_MODAL);
        trainingStage.initOwner(((Node)event.getSource()).getScene().getWindow());
        trainingStage.show();
    }

    public static void printGameResult(Game endedGame){
        System.out.print(endedGame.getRightAnswers());
    }

}
