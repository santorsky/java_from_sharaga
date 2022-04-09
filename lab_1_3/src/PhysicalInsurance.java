import java.io.Serializable;

public class PhysicalInsurance extends Insurance implements Serializable {

    private String age;

    public PhysicalInsurance() {}

    public PhysicalInsurance(String insuranceName,
                             String age,
                             int termInMonths,
                             double maxSum) {
        this.insuranceName = insuranceName;
        this.age = age;
        this.insurant = "PHYSICAL_INSURANCE";
        this.termInMonths = termInMonths;
        this.maxSum = maxSum;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return number + ") " + insuranceName + ", " + insurant + ", " + age + ", "+ termInMonths + ", " + maxSum + ", " + rate +"%";
    }


}

