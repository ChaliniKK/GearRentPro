package ui;

import dao.RentalDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReportsUI {

    public static Scene getScene(Stage stage) {

        RentalDAO dao = new RentalDAO();

        Label active = new Label(
                "Active Rentals: " + dao.getActiveRentals().size()
        );

        Label overdue = new Label(
                "Overdue Rentals: " + dao.getOverdueRentals().size()
        );

        Button back = new Button("Back");
        back.setOnAction(e -> stage.setScene(DashboardUI.getScene(stage)));

        VBox root = new VBox(15, active, overdue, back);
        root.setStyle("-fx-padding: 30; -fx-alignment: center;");

        return new Scene(root, 350, 250);
    }
}
