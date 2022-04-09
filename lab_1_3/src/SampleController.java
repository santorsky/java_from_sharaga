import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SampleController {

    @FXML
    private TextField loginFiled;

    @FXML
    private PasswordField passwordFiled;

    @FXML
    private Button signIn;

    @FXML
    private CheckBox privilegeCheck;

    @FXML
    void initialize() {
        signIn.setOnAction(event -> {

            String loginText = loginFiled.getText().trim();
            String loginPassword = passwordFiled.getText().trim();

            if ((loginText != "") && (loginPassword != "")) {
                if(!privilegeCheck.isSelected()) {
                    loginUser(loginText, loginPassword);
                } else {
                    loginAdmin(loginText, loginPassword);
                }
            }
            else
                System.out.println("Не все поля заполнены");
        });
    }

    private void loginAdmin(String loginText, String loginPassword) {
        if ((loginText.equals("admin")) && (loginPassword.equals("123"))) {
            signIn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/employeeWindow.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            System.out.println("Неверный логин или пароль");
        }
    }

    private void loginUser(String loginText, String loginPassword) {
        if ((loginText.equals("client")) && (loginPassword.equals("123"))) {
            signIn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/clientWindow.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            System.out.println("Неверный логин или пароль");
        }
    }
}
