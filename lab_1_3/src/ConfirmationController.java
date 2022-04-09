import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ConfirmationController {

    @FXML
    private Button button;


    @FXML
    void initialize() {
        button.setOnAction(event -> button.getScene().getWindow().hide());
    }
}

