import java.io.Serializable;
import java.util.ArrayList;

public class SavedInsurances implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<PhysicalInsurance> saveMyPhysicalInsuranceList;
    private ArrayList<LegalInsurance> saveMyLegalInsuranceList;
    private ArrayList<PhysicalInsurance> savePhysicalInsuranceList;
    private ArrayList<LegalInsurance> saveLegalInsuranceList;

    public SavedInsurances(ArrayList<PhysicalInsurance> saveMyPhysicalInsuranceList, ArrayList<LegalInsurance> saveMyLegalInsuranceList,
                           ArrayList<PhysicalInsurance> savePhysicalInsuranceList, ArrayList<LegalInsurance> saveLegalInsuranceList) {
        this.saveMyPhysicalInsuranceList = saveMyPhysicalInsuranceList;
        this.saveMyLegalInsuranceList = saveMyLegalInsuranceList;
        this.savePhysicalInsuranceList = savePhysicalInsuranceList;
        this.saveLegalInsuranceList = saveLegalInsuranceList;
    }

    public ArrayList<PhysicalInsurance> getSaveMyPhysicalInsuranceList() {
        return saveMyPhysicalInsuranceList;
    }

    public void setSaveMyPhysicalInsuranceList(ArrayList<PhysicalInsurance> saveMyPhysicalInsuranceList) {
        this.saveMyPhysicalInsuranceList = saveMyPhysicalInsuranceList;
    }

    public ArrayList<LegalInsurance> getSaveMyLegalInsuranceList() {
        return saveMyLegalInsuranceList;
    }

    public void setSaveMyLegalInsuranceList(ArrayList<LegalInsurance> saveMyLegalInsuranceList) {
        this.saveMyLegalInsuranceList = saveMyLegalInsuranceList;
    }

    public ArrayList<PhysicalInsurance> getSavePhysicalInsuranceList() {
        return savePhysicalInsuranceList;
    }

    public void setSavePhysicalInsuranceList(ArrayList<PhysicalInsurance> savePhysicalInsuranceList) {
        this.savePhysicalInsuranceList = savePhysicalInsuranceList;
    }

    public ArrayList<LegalInsurance> getSaveLegalInsuranceList() {
        return saveLegalInsuranceList;
    }

    public void setSaveLegalInsuranceList(ArrayList<LegalInsurance> saveLegalInsuranceList) {
        this.saveLegalInsuranceList = saveLegalInsuranceList;
    }

    @Override
    public String toString() {
        return "SavedInsurances{" +
                "saveMyPhysicalInsuranceList=" + saveMyPhysicalInsuranceList +
                ", saveMyLegalInsuranceList=" + saveMyLegalInsuranceList +
                ", savePhysicalInsuranceList=" + savePhysicalInsuranceList +
                ", saveLegalInsuranceList=" + saveLegalInsuranceList +
                '}';
    }
}