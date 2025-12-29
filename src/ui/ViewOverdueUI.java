package ui;

import dao.RentalDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewOverdueUI {

    public static Scene getScene(Stage stage) {

        TextArea area = new TextArea();
        area.setEditable(false);

        RentalDAO dao = new RentalDAO();
        for (String line : dao.getOverdueRentalDetails()) {
            area.appendText(line + "\n");
        }

        if (area.getText().isEmpty()) {
            area.setText("No overdue rentals 😌");
        }

        Button back = new Button("Back");
        back.setOnAction(e ->
                stage.setScene(DashboardUI.getScene(stage))
        );

        VBox root = new VBox(10, area, back);
        root.setStyle("-fx-padding: 20;");

        return new Scene(root, 450, 300);
    }
}
