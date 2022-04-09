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

public class AddPhysicalInsuranceClientController {

    @FXML
    private Button comeBack;

    @FXML
    private TableColumn<PhysicalInsurance, Integer> number;

    @FXML
    private TableView<PhysicalInsurance> physicalInsuranceTableView;

    @FXML
    private TableColumn<PhysicalInsurance, String> insuranceName;

    @FXML
    private TableColumn<PhysicalInsurance, String> age;

    @FXML
    private TableColumn<PhysicalInsurance, String> insurant;

    @FXML
    private TableColumn<PhysicalInsurance, Integer> termInMonths;

    @FXML
    private TableColumn<PhysicalInsurance, Double> maxSum;

    @FXML
    private TableColumn<PhysicalInsurance, Double> rate;

    @FXML
    private TextField numberAdd;

    @FXML
    private Button add;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        ArrayList<PhysicalInsurance> physicalInsurance_1 = new ArrayList<PhysicalInsurance>();
        physicalInsurance_1 = WorkingWithFiles.readSavePI(physicalInsurance_1);

        ObservableList<PhysicalInsurance> physicalInsurance = FXCollections.observableArrayList(physicalInsurance_1);

        number.setCellValueFactory(new PropertyValueFactory<>("Number"));
        insuranceName.setCellValueFactory(new PropertyValueFactory<>("InsuranceName"));
        age.setCellValueFactory(new PropertyValueFactory<>("Age"));
        insurant.setCellValueFactory(new PropertyValueFactory<>("Insurant"));
        termInMonths.setCellValueFactory(new PropertyValueFactory<>("TermInMonths"));
        maxSum.setCellValueFactory(new PropertyValueFactory<>("MaxSum"));
        rate.setCellValueFactory(new PropertyValueFactory<>("Rate"));

        physicalInsuranceTableView.setItems(physicalInsurance);

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

            PhysicalInsurance PI = new PhysicalInsurance();
            PI.setInsuranceName(physicalInsuranceList.get(addNum-1).getInsuranceName());
            PI.setInsurant(physicalInsuranceList.get(addNum-1).getInsurant());
            PI.setTermInMonths(physicalInsuranceList.get(addNum-1).getTermInMonths());
            PI.setMaxSum(physicalInsuranceList.get(addNum-1).getMaxSum());
            PI.setRate(physicalInsuranceList.get(addNum-1).getRate());
            PI.setAge(physicalInsuranceList.get(addNum-1).getAge());

            PI.setNumber(myPhysicalInsuranceList.size()+1);
            myPhysicalInsuranceList.add(PI);


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
