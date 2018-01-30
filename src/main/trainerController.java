package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class trainerController {
    @FXML
    public VBox vBox;
    @FXML
    Label LabeL;

    private Game game;
    private static String[] buttonTexts;
    private static int mode;

    public static void setButtonTexts(String[] kanaSigns) {
        buttonTexts = kanaSigns;
    }

    public static void setMode(int KANAMode) {
        mode = KANAMode;
    }


    @FXML
    public void initialize(){
        game = new Game(mode);

        vBox.setAlignment(Pos.CENTER);
        ArrayList<Button> buttons = new ArrayList<>();

        for (int i = 0; i < 46; i++){
            Button btn = new Button(buttonTexts[i]);

            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    switch (game.nextRound(btn.getText())){
                        case 2: btn.setVisible(false); break;
                        case 1: break;
                        case 0:
                            menuController.printGameResult(game);
                            ((Stage)btn.getScene().getWindow()).close(); break;
                        default: System.out.print("ERROR");
                    }
                    LabeL.setText(game.getRoundReading());
                }
            });

            GridPane.setHalignment(btn, HPos.CENTER);
            GridPane.setValignment(btn, VPos.BOTTOM);
            buttons.add(btn);
        }

        long seed = System.nanoTime();
        Collections.shuffle(buttons, new Random(seed));

        GridPane gridPane = (GridPane)vBox.getChildren().get(1);

        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                if((i == 3 && j == 0) || (i == 3 && j == 11))continue;
                gridPane.add(buttons.get(k),j,i);
                k++;
            }
        }

        LabeL.setText(game.getRoundReading());
    }

}
