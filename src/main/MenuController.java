package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Label answersCount;
    @FXML
    private Label wastedTime;

    private int kana = Kana.HIRAGANA;

    public void setKana(ActionEvent event){
        switch (((RadioButton)event.getSource()).getText()) {
            case "Hiragana":
                kana = Kana.HIRAGANA; break;
            case "Katakana":
                kana = Kana.KATAKANA; break;
            default:
                System.out.print("ERROR IN SETTING KANA");
                kana = Kana.HIRAGANA; break;
        }
    }

    public void startGame(ActionEvent event){
        switch (kana) {
            case Kana.HIRAGANA:
                TrainerController.setMode(Kana.HIRAGANA); break;
            case Kana.KATAKANA:
                TrainerController.setMode(Kana.KATAKANA); break;
            default:
                System.out.print("ERROR IN SETTING MODE");
                TrainerController.setMode(Kana.HIRAGANA); break;
        }
        Stage trainingStage = new Stage();
        try {
            VBox vBox = FXMLLoader.load(getClass().getResource("trainer.fxml"));


            trainingStage.setTitle("Kana Trainer");
            trainingStage.setScene(new Scene(vBox, 700, 500));
            //primaryStage.initStyle(StageStyle.UNDECORATED);
            trainingStage.setMaxHeight(500);
            trainingStage.setMaxWidth(700);
            trainingStage.setMinWidth(460);
            trainingStage.setMinHeight(350);
            trainingStage.initModality(Modality.WINDOW_MODAL);
            trainingStage.setOnCloseRequest(e -> TrainerController.close());

            trainingStage.initOwner(((Node)event.getSource()).getScene().getWindow());
            TrainerController.setOwner(this);

            trainingStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void printGameResult(Game endedGame){
        answersCount.setText("Right answers: " + endedGame.getRightAnswers() + "/46");
        int minutes = endedGame.getWastedTime()/60;
        wastedTime.setText("Wasted time: " + minutes + "." + endedGame.getWastedTime()%60);
    }

}
