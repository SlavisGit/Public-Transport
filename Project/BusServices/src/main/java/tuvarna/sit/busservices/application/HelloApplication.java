package tuvarna.sit.busservices.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tuvarna/sit/busservices/presentation.view/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 250, 350);
        stage.setTitle("Bus Services");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}