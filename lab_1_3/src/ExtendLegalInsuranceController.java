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

public class ExtendLegalInsuranceController {

    @FXML
    private Button comeBack;

    @FXML
    private TableView<LegalInsurance> legalInsuranceTableView;

    @FXML
    private TableColumn<LegalInsurance, Integer> number;

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
    private TextField numberExtend;

    @FXML
    private Button extend;

    @FXML
    private TextField numberMonths;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        ArrayList<LegalInsurance> legalInsurance_1 = new ArrayList<LegalInsurance>();
        legalInsurance_1 = WorkingWithFiles.readSaveMLI(legalInsurance_1);

        ObservableList<LegalInsurance> legalInsurance = FXCollections.observableArrayList(legalInsurance_1);

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

        extend.setOnAction(event -> {

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

            int exNum = Integer.parseInt(numberExtend.getText().trim());
            int count = Integer.parseInt(numberMonths.getText().trim());

            myLegalInsuranceList.get(exNum-1).setTermInMonths(count + myLegalInsuranceList.get(exNum-1).getTermInMonths());

            try {
                WorkingWithFiles.dataSave(myPhysicalInsuranceList, myLegalInsuranceList, physicalInsuranceList, legalInsuranceList);
            } catch (IOException e) {
                e.printStackTrace();
            }

            extend.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/extendLegalInsurance.fxml"));

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
