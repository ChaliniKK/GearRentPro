package ui;

import dao.EquipmentDAO;
import entity.Equipment;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewEquipmentUI {

    public static Scene getScene(Stage stage) {

        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 20;");

        EquipmentDAO dao = new EquipmentDAO();

        for (Equipment e : dao.getAllEquipment()) {
            root.getChildren().add(
                    new javafx.scene.control.Label(
                            e.getEquipmentId() + " | " +
                                    e.getBrand() + " " + e.getModel() + " | " +
                                    e.getStatus()

                    )
            );
        }

        Button back = new Button("Back");
        back.setOnAction(e -> stage.setScene(DashboardUI.getScene(stage)));

        root.getChildren().add(back);

        return new Scene(root, 400, 300);
    }
}
