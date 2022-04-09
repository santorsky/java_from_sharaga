import java.io.Serializable;

public abstract class Insurance implements Serializable {

    protected int number;

    protected String insuranceName;

    protected String insurant;

    protected int termInMonths;

    protected double maxSum;

    protected double rate;

    public int getTermInMonths() {
        return termInMonths;
    }

    public void setTermInMonths(int termInMonths) {
        this.termInMonths = termInMonths;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getInsurant() {
        return insurant;
    }

    public void setInsurant(String insurant) {
        this.insurant = insurant;
    }

    public double getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(double maxSum) {
        this.maxSum = maxSum;
    }

    public double getRate() {
        return rate;
    }
}
