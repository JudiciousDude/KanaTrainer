package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox vBox = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Kana Trainer");
        primaryStage.setScene(new Scene(vBox, 700, 500));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setMaxHeight(500);
        primaryStage.setMaxWidth(700);
        primaryStage.setMinWidth(460);
        primaryStage.setMinHeight(350);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
