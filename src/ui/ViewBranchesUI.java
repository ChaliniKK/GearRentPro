package ui;

import dao.BranchDAO;
import entity.Branch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewBranchesUI {

    public static Scene getScene(Stage stage) {

        TextArea area = new TextArea();
        area.setEditable(false);

        BranchDAO dao = new BranchDAO();
        for (Branch b : dao.getAllBranches()) {
            area.appendText(
                    b.getBranchCode() + " - " + b.getName() + "\n"
            );
        }

        Button back = new Button("Back");
        back.setOnAction(e ->
                stage.setScene(DashboardUI.getScene(stage))
        );

        VBox root = new VBox(10, area, back);
        root.setStyle("-fx-padding: 20;");

        return new Scene(root, 400, 300);
    }
}
