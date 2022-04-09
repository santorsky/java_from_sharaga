import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ShowLegalInsuranceClientController {

    @FXML
    private Button comeBack;

    @FXML
    private TableColumn<LegalInsurance, Integer> number;

    @FXML
    private TableView<LegalInsurance> legalInsuranceTableView;

    @FXML
    private TableColumn<LegalInsurance, String> insuranceName;

    @FXML
    private TableColumn<LegalInsurance, String> insurant;

    @FXML
    private TableColumn<LegalInsurance, String> legalEntityType;

    @FXML
    private TableColumn<LegalInsurance, Integer> termInMonths;

    @FXML
    private TableColumn<LegalInsurance, Double> maxSum;

    @FXML
    private TableColumn<LegalInsurance, Double> rate;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        ArrayList<LegalInsurance> legalInsuranceList = new ArrayList<LegalInsurance>();
        legalInsuranceList = WorkingWithFiles.readSaveLI(legalInsuranceList);

        ObservableList<LegalInsurance> legalInsurance = FXCollections.observableArrayList(legalInsuranceList);

        try {
            number.setCellValueFactory(new PropertyValueFactory<>("Number"));
            insuranceName.setCellValueFactory(new PropertyValueFactory<>("InsuranceName"));
            insurant.setCellValueFactory(new PropertyValueFactory<>("Insurant"));
            legalEntityType.setCellValueFactory(new PropertyValueFactory<>("LegalEntityType"));
            termInMonths.setCellValueFactory(new PropertyValueFactory<>("TermInMonths"));
            maxSum.setCellValueFactory(new PropertyValueFactory<>("MaxSum"));
            rate.setCellValueFactory(new PropertyValueFactory<>("Rate"));

            legalInsuranceTableView.setItems(legalInsurance);
        } catch (Exception ex) {
            System.out.println(WorkingWithFiles.ANSI_RED + ex.getMessage() + WorkingWithFiles.ANSI_RESET);
        }

        comeBack.setOnAction(event -> {
            comeBack.getScene().getWindow().hide();

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
        });
    }

}
