package ua.com.rozdobudko;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ua.com.rozdobudko.Controllers.Controller;

/**
 * @author Oleksii Rozdobudko rozdobudko.com.ua
 * @version 1.1.1
 * @since 2017-08-26
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/main.fxml"));
        Parent fxmlMain = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        controller.setMainStage(primaryStage);
        primaryStage.setTitle("Транслитератор v1.1.1");
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        primaryStage.setScene(new Scene(fxmlMain));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}