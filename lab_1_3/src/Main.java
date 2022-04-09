import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

import static com.sun.xml.internal.bind.v2.schemagen.Util.equalsIgnoreCase;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("AllSave");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    public static double checkAge(PhysicalInsurance PI) {
        double factor = 2;
        if (equalsIgnoreCase(PI.getAge(), "ADULT")) factor = 1.2;
        if (equalsIgnoreCase(PI.getAge(), "MINOR")) factor = 1.0;
        return factor;
    }

    public static double checkLegalEntityType(LegalInsurance LI) {
        double factor = 2;
        if (equalsIgnoreCase(LI.getLegalEntityType(), "ЗАО")) factor = 1.3;
        if (equalsIgnoreCase(LI.getLegalEntityType(), "ООО")) factor = 1.0;
        if (equalsIgnoreCase(LI.getLegalEntityType(), "ОАО")) factor = 0.9;
        return factor;
    }

    public static void checkRatePhysical(PhysicalInsurance PI) {
        double temp;
        temp = (0.2* checkAge(PI));
        if (equalsIgnoreCase(PI.insuranceName, "ТАСК")) PI.setRate(0.12* checkAge(PI));
        if (equalsIgnoreCase(PI.insuranceName, "ПромТрансИнвест")) PI.setRate(0.14* checkAge(PI));
        temp = temp*10000;
        temp = Math.round(temp)/100;
        PI.setRate(temp);
    }

    public static void checkRateLegal(LegalInsurance LI) {
        double temp;
        temp = (0.2* checkLegalEntityType(LI));
        if (equalsIgnoreCase(LI.insuranceName, "ТАСК")) LI.setRate(0.12* checkLegalEntityType(LI));
        if (equalsIgnoreCase(LI.insuranceName, "ПромТрансИнвест")) LI.setRate(0.14* checkLegalEntityType(LI));
        temp = temp*10000;
        temp = Math.round(temp)/100;
        LI.setRate(temp);
    }


    public static ArrayList<LegalInsurance> checkLIL(ArrayList<LegalInsurance> LIL, int num) {
            for (int i = num; i < LIL.size(); i++) {
                LIL.get(i).setNumber(LIL.get(i).getNumber() - 1);
            }
        return LIL;
    }

    public static ArrayList<PhysicalInsurance> checkPIL(ArrayList<PhysicalInsurance> PIL, int num) {
            for (int i = num; i < PIL.size(); i++) {
                PIL.get(i).setNumber(PIL.get(i).getNumber() - 1);
            }
        return PIL;
    }


    public static void main(String... args) {
        launch(args);
    }
}