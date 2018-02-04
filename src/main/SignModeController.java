package main;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SignModeController {
    @FXML
    public VBox vBox;
    @FXML
    private Label reading;
    @FXML
    private ProgressBar progress;

    private static Game game;
    private static String[] buttonTexts;
    private static int mode;
    private static MenuController menuController;
    private static boolean randomButtons = false;

    public static void setOwner(MenuController owner){
        menuController = owner;
    }

    public static void setHardMode(boolean hard){
        randomButtons = hard;
    }

    public static void setButtonTexts(String[] kanaSigns) {
        buttonTexts = kanaSigns;
    }

    public static void setMode(int KANAMode) {
        mode = KANAMode;
    }

    @FXML
    public void initialize(){
        progress.setProgress(0);
        game = new Game(mode);
        ArrayList<Button> buttons = new ArrayList<>();

        for (int i = 0; i < 46; i++){
            Button btn = new Button(buttonTexts[i]);

            btn.setOnAction(event -> {
                progress.setProgress(progress.getProgress() + game.getProgressBarStep());
                switch (game.nextRound(btn.getText())){
                    case 2: btn.setDisable(true); btn.setOpacity(0.2); break;
                    case 1: break;
                    case 0:
                        close();
                        ((Stage)btn.getScene().getWindow()).close(); break;
                    default: System.out.print("ERROR");
                }
                reading.setText(game.getRoundReading());
            });
            GridPane.setHalignment(btn, HPos.CENTER);
            GridPane.setValignment(btn, VPos.BOTTOM);
            btn.setFocusTraversable(false);
            buttons.add(btn);
        }

        if(randomButtons) {
            long seed = System.nanoTime();
            Collections.shuffle(buttons, new Random(seed));
        }

        GridPane gridPane = (GridPane)vBox.getChildren().get(2);

        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                if((i == 3 && j == 0) || (i == 3 && j == 11))continue;
                gridPane.add(buttons.get(k),j,i);
                k++;
            }
        }

        reading.setText(game.getRoundReading());
        game.start();
    }

    public static void close(){
        game.stop();
        menuController.printGameResult(game);
    }
}
