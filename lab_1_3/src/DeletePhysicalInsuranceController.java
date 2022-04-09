import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class DeletePhysicalInsuranceController {

    @FXML
    private Button comeBack;

    @FXML
    private Button delete;

    @FXML
    private TableView<PhysicalInsurance> physicalInsuranceTableView;

    @FXML
    private TableColumn<PhysicalInsurance, Integer> number;

    @FXML
    private TableColumn<PhysicalInsurance, String> insuranceName;

    @FXML
    private TableColumn<PhysicalInsurance, String> insurant;

    @FXML
    private TableColumn<PhysicalInsurance, String> age;

    @FXML
    private TableColumn<PhysicalInsurance, Integer> termInMonths;

    @FXML
    private TableColumn<PhysicalInsurance, Double> maxSum;

    @FXML
    private TableColumn<PhysicalInsurance, Double> rate;

    @FXML
    private TextField numberDelete;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        ArrayList<PhysicalInsurance> physicalInsuranceList_1 = new ArrayList<PhysicalInsurance>();
        physicalInsuranceList_1 = WorkingWithFiles.readSavePI(physicalInsuranceList_1);

        ObservableList<PhysicalInsurance> physicalInsurance = FXCollections.observableArrayList(physicalInsuranceList_1);

        try {
            number.setCellValueFactory(new PropertyValueFactory<>("Number"));
            insuranceName.setCellValueFactory(new PropertyValueFactory<>("InsuranceName"));
            insurant.setCellValueFactory(new PropertyValueFactory<>("Insurant"));
            age.setCellValueFactory(new PropertyValueFactory<>("Age"));
            termInMonths.setCellValueFactory(new PropertyValueFactory<>("TermInMonths"));
            maxSum.setCellValueFactory(new PropertyValueFactory<>("MaxSum"));
            rate.setCellValueFactory(new PropertyValueFactory<>("Rate"));

            physicalInsuranceTableView.setItems(physicalInsurance);
        } catch (Exception ex) {
            System.out.println(WorkingWithFiles.ANSI_RED + ex.getMessage() + WorkingWithFiles.ANSI_RESET);
        }

        comeBack.setOnAction(event -> {
            comeBack.getScene().getWindow().hide();

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
        });

        delete.setOnAction(event -> {

            ArrayList<PhysicalInsurance> physicalInsuranceList = new ArrayList<PhysicalInsurance>();
            try {
                physicalInsuranceList = WorkingWithFiles.readSavePI(physicalInsuranceList);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<PhysicalInsurance> myPhysicalInsuranceList = new ArrayList<PhysicalInsurance>();
            try {
                myPhysicalInsuranceList = WorkingWithFiles.readSaveMPI(myPhysicalInsuranceList);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            ArrayList<LegalInsurance> legalInsuranceList = new ArrayList<LegalInsurance>();
            try {
                legalInsuranceList = WorkingWithFiles.readSaveLI(legalInsuranceList);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<LegalInsurance> myLegalInsuranceList = new ArrayList<LegalInsurance>();
            try {
                myLegalInsuranceList = WorkingWithFiles.readSaveMLI(myLegalInsuranceList);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            int delNum = Integer.parseInt(numberDelete.getText().trim());

            physicalInsuranceList.remove(delNum - 1);

            physicalInsuranceList = Main.checkPIL(physicalInsuranceList, delNum);

            try {
                WorkingWithFiles.dataSave(myPhysicalInsuranceList, myLegalInsuranceList, physicalInsuranceList, legalInsuranceList);
            } catch (IOException e) {
                e.printStackTrace();
            }

            delete.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/deletePhysicalInsurance.fxml"));

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
