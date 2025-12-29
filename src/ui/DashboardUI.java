package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardUI {

    public static Scene getScene(Stage stage) {

        Button viewBranches = new Button("View Branches");
        Button viewEquipment = new Button("View Equipment");
        Button reports = new Button("Reports");
        Button overdueBtn = new Button("View Overdue Rentals");


        viewBranches.setOnAction(e ->
                stage.setScene(ViewBranchesUI.getScene(stage))
        );

        overdueBtn.setOnAction(e ->
                stage.setScene(ViewOverdueUI.getScene(stage))
        );

        viewEquipment.setOnAction(e ->
                stage.setScene(ViewEquipmentUI.getScene(stage))
        );

        reports.setOnAction(e ->
                stage.setScene(ReportsUI.getScene(stage))
        );

        VBox root = new VBox(15, viewBranches, viewEquipment, overdueBtn, reports);

        root.setStyle("-fx-padding: 40; -fx-alignment: center;");

        return new Scene(root, 400, 250);


    }
}
