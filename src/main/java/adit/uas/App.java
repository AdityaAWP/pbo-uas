package adit.uas;

import adit.uas.model.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Database.getConnection(); // Ensure the connection is established
        Database.createTable(); // Ensure the tables are created

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adit/uas/Primary.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Travel Management System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
