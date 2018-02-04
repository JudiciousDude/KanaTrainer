package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Label answersCount;
    @FXML
    private Label wastedTime;
    @FXML
    CheckBox hard;
    @FXML
    ToggleGroup version;

    private int kana = Kana.HIRAGANA;
    private int mode = 2;

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

    public void setMode(ActionEvent e){
        RadioButton call = (RadioButton)e.getSource();
        switch (call.getText()) {
            case "Sign mode":
                call.setVisible(false);
                hard.setVisible(true);
                mode = 1; break;
            case "Reading mode":
                ((RadioButton)version.getToggles().get(0)).setVisible(true);
                hard.setSelected(false);
                hard.setVisible(false);
                mode = 2; break;
            default:
                System.out.print("ERROR IN SETTING MODE");
                mode = 1; break;
        }
    }

    //CALLING TRAINING MODE
    public void startGame(ActionEvent event){
        Stage trainingStage = new Stage();
        Parent scene;

        switch (mode) {
            case 1:
                SignModeController.setHardMode(hard.isSelected());
                SignModeController.setOwner(this);

                if(kana == Kana.HIRAGANA)SignModeController.setMode(Kana.HIRAGANA);
                    else SignModeController.setMode(Kana.KATAKANA);

                try{scene = FXMLLoader.load(getClass().getResource("sigh_mode.fxml"));
                    trainingStage.setScene(new Scene(scene, 700, 500));}
                catch (IOException e){e.printStackTrace();}
                trainingStage.setOnCloseRequest(e -> SignModeController.close());

                break;

            case 2:
                try{scene = FXMLLoader.load(getClass().getResource("reading_mode.fxml"));
                    trainingStage.setScene(new Scene(scene, 700, 500));}
                catch (IOException e){e.printStackTrace();}

                break;

            default:
                System.out.println("ERROR IN MODE");
        }

        trainingStage.setTitle("Kana Trainer");
        //trainingStage.initStyle(StageStyle.UNDECORATED);
        trainingStage.setMaxHeight(500);
        trainingStage.setMaxWidth(700);
        trainingStage.setMinWidth(460);
        trainingStage.setMinHeight(350);
        trainingStage.initModality(Modality.WINDOW_MODAL);
        trainingStage.initOwner(((Node)event.getSource()).getScene().getWindow());

        trainingStage.show();
    }

    public void printGameResult(Game endedGame){
        answersCount.setText("Right answers: " + endedGame.getRightAnswers() + "/46");
        int minutes = endedGame.getWastedTime()/60;
        wastedTime.setText("Wasted time: " + minutes + "." + endedGame.getWastedTime()%60);
    }

}
