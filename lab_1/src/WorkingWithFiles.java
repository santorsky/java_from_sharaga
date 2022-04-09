import java.io.*;
import java.util.ArrayList;

public class WorkingWithFiles {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void dataSave (ArrayList<PhysicalInsurance> MyPhysicalInsuranceList, ArrayList<LegalInsurance> MyLegalInsuranceList,
                                 ArrayList<PhysicalInsurance> PhysicalInsuranceList, ArrayList<LegalInsurance> LegalInsuranceList) throws IOException {

        SavedInsurances savedInsurances = new SavedInsurances(MyPhysicalInsuranceList, MyLegalInsuranceList,
                PhysicalInsuranceList, LegalInsuranceList);

        try {
            FileOutputStream outputStream = new FileOutputStream("D:\\IntelliJ IDEA\\lab_1\\SAVES\\save.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(savedInsurances);
            objectOutputStream.close();
        } catch (IOException ex) {
            System.out.println(ANSI_RED + ex.getMessage() + ANSI_RESET);
        }
    }


    public static ArrayList<PhysicalInsurance> readSavePI(ArrayList<PhysicalInsurance> PhysicalInsuranceList) throws IOException, ClassNotFoundException {

        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\IntelliJ IDEA\\lab_1\\SAVES\\save.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            SavedInsurances savedInsurances = (SavedInsurances) objectInputStream.readObject();
            PhysicalInsuranceList = savedInsurances.getSavePhysicalInsuranceList();
        } catch (IOException ex) {
            System.out.println(ANSI_RED + ex.getMessage() + ANSI_RESET);
        }
        return PhysicalInsuranceList;
    }

    public static ArrayList<PhysicalInsurance> readSaveMPI(ArrayList<PhysicalInsurance> PhysicalInsurance) throws IOException, ClassNotFoundException {

        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\IntelliJ IDEA\\lab_1\\SAVES\\save.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            SavedInsurances savedInsurances = (SavedInsurances) objectInputStream.readObject();
            PhysicalInsurance = savedInsurances.getSaveMyPhysicalInsuranceList();
        } catch (IOException ex) {
            System.out.println(ANSI_RED + ex.getMessage() + ANSI_RESET);
        }
        return PhysicalInsurance;
    }

    public static ArrayList<LegalInsurance> readSaveLI(ArrayList<LegalInsurance> LegalInsuranceList) throws IOException, ClassNotFoundException {

        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\IntelliJ IDEA\\lab_1\\SAVES\\save.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            SavedInsurances savedInsurances = (SavedInsurances) objectInputStream.readObject();
            LegalInsuranceList = savedInsurances.getSaveLegalInsuranceList();
        }
        catch (IOException ex) {
            System.out.println(ANSI_RED + ex.getMessage() + ANSI_RESET);
        }
        return LegalInsuranceList;
    }

    public static ArrayList<LegalInsurance> readSaveMLI(ArrayList<LegalInsurance> LegalInsuranceList) throws IOException, ClassNotFoundException {

        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\IntelliJ IDEA\\lab_1\\SAVES\\save.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            SavedInsurances savedInsurances = (SavedInsurances) objectInputStream.readObject();
            LegalInsuranceList = savedInsurances.getSaveMyLegalInsuranceList();
        } catch (IOException ex) {
            System.out.println(ANSI_RED + ex.getMessage() + ANSI_RESET);
        }
        return LegalInsuranceList;
    }

}
