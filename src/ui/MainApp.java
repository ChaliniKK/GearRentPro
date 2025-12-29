package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import dao.UserDAO;
import entity.User;
import util.Session;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("GearRent Pro");
        Button loginBtn = new Button("Login");

        loginBtn.setOnAction(e -> {

            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserByUsername("admin");

            if (user != null) {
                Session.setCurrentUser(user);
                stage.setScene(DashboardUI.getScene(stage));
            }
        });


        VBox root = new VBox(15, title, loginBtn);
        root.setStyle("-fx-padding: 40; -fx-alignment: center;");

        stage.setScene(new Scene(root, 300, 200));
        stage.setTitle("Login");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
