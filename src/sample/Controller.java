package sample;

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

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Controller {

    @FXML
    private VBox vBox;
    @FXML
    Label LabeL;

    private int rightAnswers = 0;
    private HashMap hashMap;
    private Kana r;
    private LinkedList<Kana> round;


    public void check(String txt){
        if(round.isEmpty()){
            System.out.print("end");
            return;
        }
        if (hashMap.get(txt) == r.getReading()){
            rightAnswers++;
            System.out.print('+');
        }
        r = round.poll();
        LabeL.setText(r.getReading());
    }


    @FXML
    public void initialize(){
        vBox.setAlignment(Pos.CENTER);
        ArrayList<Button> buttons = new ArrayList<>();

        for (int i = 0; i < 46; i++){
            Button btn = new Button(Kana.hiraganaSigns[i]);

            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    check(btn.getText());
                }
            });

            GridPane.setHalignment(btn, HPos.CENTER);
            GridPane.setValignment(btn, VPos.BOTTOM);
            buttons.add(btn);
        }

        long seed = System.nanoTime();
        Collections.shuffle(buttons, new Random(seed));

        GridPane gridPane = (GridPane)vBox.getChildren().get(1);

        int k =0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                if((i == 3 && j == 0) || (i == 3 && j == 11))continue;
                gridPane.add(buttons.get(k),j,i);
                k++;
            }
        }

        hashMap = Kana.getKanaMap(Kana.HIRAGANA);
        round = Kana.getKanaList(Kana.HIRAGANA);
        seed = System.currentTimeMillis();
        Collections.shuffle(round, new Random(seed));
        r = round.poll();
        LabeL.setText(r.getReading());
    }

}
