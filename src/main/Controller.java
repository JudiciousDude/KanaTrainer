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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Controller {
    @FXML
    private VBox vBox;
    @FXML
    Label LabeL;

    private Game game;
    public static String[] buttonTexts;

    @FXML
    public void initialize(){
        game = new Game(Kana.HIRAGANA);
        vBox.setAlignment(Pos.CENTER);
        ArrayList<Button> buttons = new ArrayList<>();

        for (int i = 0; i < 46; i++){
            Button btn = new Button(buttonTexts[i]);

            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(game.nextRound(btn.getText()))
                        btn.setVisible(false);
                    LabeL.setText(game.getRoundLabel());
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

        LabeL.setText(game.getRoundLabel());

    }

}
