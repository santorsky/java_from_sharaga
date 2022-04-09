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

public class AddLegalInsuranceClientController {

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
    private TextField numberAdd;

    @FXML
    private Button add;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        ArrayList<LegalInsurance> legalInsuranceList_1 = new ArrayList<LegalInsurance>();
        legalInsuranceList_1 = WorkingWithFiles.readSaveLI(legalInsuranceList_1);

        ObservableList<LegalInsurance> legalInsurance = FXCollections.observableArrayList(legalInsuranceList_1);

        number.setCellValueFactory(new PropertyValueFactory<>("Number"));
        insuranceName.setCellValueFactory(new PropertyValueFactory<>("InsuranceName"));
        insurant.setCellValueFactory(new PropertyValueFactory<>("Insurant"));
        legalEntityType.setCellValueFactory(new PropertyValueFactory<>("LegalEntityType"));
        termInMonths.setCellValueFactory(new PropertyValueFactory<>("TermInMonths"));
        maxSum.setCellValueFactory(new PropertyValueFactory<>("MaxSum"));
        rate.setCellValueFactory(new PropertyValueFactory<>("Rate"));

        legalInsuranceTableView.setItems(legalInsurance);

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

        add.setOnAction(event -> {
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

            int addNum = Integer.parseInt(numberAdd.getText().trim());

            LegalInsurance LI = new LegalInsurance();
            LI.setInsuranceName(legalInsuranceList.get(addNum-1).getInsuranceName());
            LI.setInsurant(legalInsuranceList.get(addNum-1).getInsurant());
            LI.setTermInMonths(legalInsuranceList.get(addNum-1).getTermInMonths());
            LI.setMaxSum(legalInsuranceList.get(addNum-1).getMaxSum());
            LI.setRate(legalInsuranceList.get(addNum-1).getRate());
            LI.setLegalEntityType(legalInsuranceList.get(addNum-1).getLegalEntityType());

            LI.setNumber(myLegalInsuranceList.size()+1);
            myLegalInsuranceList.add(LI);

            try {
                WorkingWithFiles.dataSave(myPhysicalInsuranceList, myLegalInsuranceList, physicalInsuranceList, legalInsuranceList);
            } catch (IOException e) {
                e.printStackTrace();
            }

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/confirmation.fxml"));

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
