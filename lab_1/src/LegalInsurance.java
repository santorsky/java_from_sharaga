import java.io.Serializable;

public class LegalInsurance extends Insurance implements Serializable {


    private String legalEntityType;

    public LegalInsurance() {}


    public LegalInsurance(String insuranceName,
                          String legalEntityType,
                          int termInMonths,
                          double maxSum) {
        this.insuranceName = insuranceName;
        this.legalEntityType = legalEntityType;
        this.insurant = "LEGAL_INSURANCE";
        this.termInMonths = termInMonths;
        this.maxSum = maxSum;
    }

    public String getLegalEntityType() {
        return legalEntityType;
    }

    public void setLegalEntityType(String legalEntityType) {
        this.legalEntityType = legalEntityType;
    }

    @Override
    public String toString() {
        return number + ") " + insuranceName + ", " + insurant + ", " + legalEntityType + ", " + termInMonths + ", " + maxSum + ", " + rate +"%";
    }

}

