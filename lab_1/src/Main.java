import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

import static com.sun.xml.internal.bind.v2.schemagen.Util.equalsIgnoreCase;


public class Main {
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




    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void pressAnyKey(){
        System.out.println("Нажмите Enter чтобы продолжить");
        try
        {
            System.in.read();
        }
        catch (Exception e)
        {}
    }

    public static void viewPhysicalInsuranceList(ArrayList<PhysicalInsurance> PI) {
        int i = 1;
        for (PhysicalInsurance c : PI) {
            System.out.println(c.toString());
            i++;
        }
    }

    public static void viewLegalInsuranceList(ArrayList<LegalInsurance> LI) {
        int i = 1;
        for (LegalInsurance c : LI) {
            System.out.println(c.toString());
            i++;
        }
    }

    public static void enterAccount (User user) throws NoIntException {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите логин");
        String log = input.next();
        int temp = 0;

        if (log.equals(user.getLogin())  == false)
        {
            System.out.println(ANSI_RED + "Неверное имя пользователя" + ANSI_RESET);
            System.out.println("1 - попробовать ещё раз, другое - выйти");
            temp = Test.enterInt();
            if (temp == 1) {
                enterAccount(user);
            } else {
                System.exit(0);
            }
        } else enterPassword(user);
    }

    public static void enterPassword (User user) throws NoIntException {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите пароль");
        String pas = input.next();
        int temp = 0;
        if (pas.equals(user.getPassword()) == false)
        {
            System.out.println(ANSI_RED + "Неверный пароль" + ANSI_RESET);
            System.out.println("1 - попробовать ещё раз, другое - выйти");
            temp = Test.enterInt();
            if (temp == 1) {
                enterPassword(user);
            } else {
                System.exit(0);
            }
        }
        else  System.out.println("Вход успешно выполнен");

    }

    public static ArrayList<PhysicalInsurance> deletePI(ArrayList<PhysicalInsurance> PIL) throws NoIntException {
        viewPhysicalInsuranceList(PIL);
        System.out.println("Введите номер удаляемого предложения");
        int num = Test.enterInt() - 1;
        try {
            if (num <= PIL.size()) {


                PIL.remove(num);
                PIL = Main.checkPIL(PIL, num);

            } else {
                throw new IncorrectNumberException(ANSI_RED + "Полиса с таким номером не существует!" + ANSI_RESET);
            }
        } catch (IncorrectNumberException e) {
            System.out.println(e.getMessage());
            PIL = deletePI(PIL);
        }
        return PIL;
    }

    public static ArrayList<LegalInsurance> deleteLI (ArrayList<LegalInsurance> LIL) throws NoIntException {
        viewLegalInsuranceList(LIL);
        System.out.println("Введите номер удаляемого предложения");
        int num = Test.enterInt() - 1;
        try {
            if (num <= LIL.size()) {
                LIL.remove(num);
                LIL = Main.checkLIL(LIL, num);
            } else {
                throw new IncorrectNumberException(ANSI_RED + "Полиса с таким номером не существует!" + ANSI_RESET);
            }
        } catch (IncorrectNumberException e) {
            System.out.println(e.getMessage());
            LIL = deleteLI(LIL);
        }
        return LIL;
    }

    public static int checkNumber(int k) throws NoIntException {
        boolean check = false;
        int num = 0;
        do {
            num = Test.enterInt();
            try {
                if (num <= k) {
                    check = true;
                } else {
                    throw new IncorrectNumberException(ANSI_RED + "Полиса с таким номером не существует!" + ANSI_RESET);
                }
            } catch (IncorrectNumberException e) {
                System.out.println(e.getMessage() + "\nПопробуйте снова");
            }
        } while (check == false);
        return num - 1;
    }

    public static void Save(ArrayList<PhysicalInsurance> myPhysicalInsuranceList, ArrayList<LegalInsurance> myLegalInsuranceList,
                            ArrayList<PhysicalInsurance> physicalInsuranceList, ArrayList<LegalInsurance> legalInsuranceList) throws IOException, NoIntException, ClassNotFoundException {
        System.out.println("Сохранить изменения?\n 1 - да\n 2 - нет");
        int t = Test.enterInt();
        switch (t) {
            case 1:
                WorkingWithFiles.dataSave(myPhysicalInsuranceList, myLegalInsuranceList, physicalInsuranceList, legalInsuranceList);
                System.out.println("Данные сохранены!");
                break;
            case 2:
                break;
            default:
                System.out.println("Не выбрано действие, попробуйте снова");
                Save(myPhysicalInsuranceList, myLegalInsuranceList, physicalInsuranceList, legalInsuranceList);
                break;
        }

    }

    public static void main(String... args) throws NoIntException, NoDoubleException, IOException, ClassNotFoundException {

        ArrayList<PhysicalInsurance> myPhysicalInsuranceList = new ArrayList<PhysicalInsurance>();
        ArrayList<LegalInsurance> myLegalInsuranceList = new ArrayList<LegalInsurance>();

        ArrayList<PhysicalInsurance> physicalInsuranceList = new ArrayList<PhysicalInsurance>();
        ArrayList<LegalInsurance> legalInsuranceList = new ArrayList<LegalInsurance>();

        User admin = new User("admin", "123");
        User client = new User("client", "123");



        Scanner input = new Scanner(System.in);
        boolean check_1 = true;
        do
        {
            boolean check_2 = true;
            myPhysicalInsuranceList = WorkingWithFiles.readSaveMPI(myPhysicalInsuranceList);
            legalInsuranceList = WorkingWithFiles.readSaveLI(legalInsuranceList);
            myLegalInsuranceList = WorkingWithFiles.readSaveMLI(myLegalInsuranceList);
            physicalInsuranceList = WorkingWithFiles.readSavePI(physicalInsuranceList);
            System.out.println("Введите 1 если хотите войти как сотрудник\nВведите 2 если хотите войти как клиент\nВведите любое другое число чтобы выйти");
            int t = Test.enterInt();
            if (t == 1) {
                enterAccount(admin);
                do {
                    System.out.println("1 - просмотреть предложения");
                    System.out.println("2 - добавить предложение");
                    System.out.println("3 - удалить предложение");
                    System.out.println("4 - выход из аккаунта");
                    System.out.println("0 - выход из программы");
                    int a = Test.enterInt();
                    switch (a) {
                        case 0: {
                            Save(myPhysicalInsuranceList, myLegalInsuranceList, physicalInsuranceList, legalInsuranceList);
                            System.exit(0);
                        }
                        break;
                        case 1: {
                            int r, i = 1;
                            System.out.println("1 - просмотреть PHYSICAL_INSURANCE, 2 - просмотреть LEGAL_INSURANCE, другое - отмена");
                            r = Test.enterInt();
                            switch (r) {
                                case 1:
                                    viewPhysicalInsuranceList(physicalInsuranceList);
                                    break;
                                case 2:
                                    viewLegalInsuranceList(legalInsuranceList);
                                    break;
                                default: break;
                            }
                            pressAnyKey();
                        }
                        break;
                        case 2: {
                            System.out.println("1 - добавить PHYSICAL_INSURANCE, другое - добавить LEGAL_INSURANCE");
                            int x = Test.enterInt();
                            if (x == 1) {
                                System.out.println("Введите название страховой компании");
                                String insuranceCompany = input.next();

                                System.out.println("Страхуемое лицо совершеннолетнее?");
                                System.out.println("1 - да, другое - нет");
                                int y = Test.enterInt();
                                String type = "";
                                if (y == 1) type = "ADULT";
                                    else type = "MINOR";

                                System.out.println("Введите срок договора (в месяцах)");
                                int months = Test.enterInt();
                                System.out.println("Введите сумму страхового полиса");
                                double sum = Test.enterDouble();

                                PhysicalInsurance PI = new PhysicalInsurance(insuranceCompany, type, months, sum);
                                checkRatePhysical(PI);
                                PI.setNumber(physicalInsuranceList.size()+1);
                                physicalInsuranceList.add(PI);
                                PI = null;


                                pressAnyKey();
                            } else {
                                System.out.println("Введите название страховой компании");
                                String insuranceCompany = input.next();

                                System.out.println("Выберите тип юр. лица");
                                System.out.println("1 - ООО, 2 - ОАО, другое - ЗАО");
                                int y = Test.enterInt();
                                String type = "";
                                if (y == 1) type = "ООО";
                                    else if (y == 2) type = "ОАО";
                                        else type = "ЗАО";

                                System.out.println("Введите срок договора (в месяцах)");
                                int months = Test.enterInt();
                                System.out.println("Введите сумму страхового полиса");
                                double sum = input.nextDouble();

                                LegalInsurance LI = new LegalInsurance(insuranceCompany, type, months, sum);
                                checkRateLegal(LI);
                                LI.setNumber(legalInsuranceList.size()+1);
                                legalInsuranceList.add(LI);
                                LI = null;


                                pressAnyKey();
                            }
                        }
                        break;
                        case 3: {
                            System.out.println("1 - удалить PHYSICAL_INSURANCE, другое - удалить LEGAL_INSURANCE");
                            int x = Test.enterInt();
                            if (x ==1) {

                                physicalInsuranceList = deletePI(physicalInsuranceList);


                                pressAnyKey();
                            } else {
                                int i = 1;
                                    legalInsuranceList = deleteLI(legalInsuranceList);



                                    pressAnyKey();
                            }
                        }
                        break;
                        case 4: {
                            Save(myPhysicalInsuranceList, myLegalInsuranceList, physicalInsuranceList, legalInsuranceList);
                            check_2 = false;
                        }
                        break;
                        default: {
                            System.out.println("\n\nВы не выбрали функцию, попробуйте снова");
                            pressAnyKey();
                            break;
                        }
                    }

                } while (check_2 == true);
            } else if (t == 2) {
                enterAccount(client);
                do {
                    System.out.println("1 - просмотреть предложения");
                    System.out.println("2 - просмотреть свои страховки");
                    System.out.println("3 - оформить страховку");
                    System.out.println("4 - разорвать договор");
                    System.out.println("5 - продлить договор");
                    System.out.println("6 - выход из аккаунта");
                    System.out.println("0 - выход из программы");
                    int a = Test.enterInt();
                    switch (a) {
                        case 0: {
                            Save(myPhysicalInsuranceList, myLegalInsuranceList, physicalInsuranceList, legalInsuranceList);
                            System.exit(0);
                        }
                        break;
                        case 1: {
                            int r, i = 1;
                            System.out.println("1 - просмотреть PHYSICAL_INSURANCE, 2 - просмотреть LEGAL_INSURANCE, другое - отмена");
                            r = Test.enterInt();
                            switch (r) {
                                case 1:
                                    viewPhysicalInsuranceList(physicalInsuranceList);
                                    break;
                                case 2:
                                    viewLegalInsuranceList(legalInsuranceList);
                                    break;
                                default: break;
                            }
                            pressAnyKey();
                        }
                        break;
                        case 2: {
                            int r, i = 1;
                            System.out.println("1 - просмотреть PHYSICAL_INSURANCE, 2 - просмотреть LEGAL_INSURANCE, другое - отмена");
                            r = Test.enterInt();
                            switch (r) {
                                case 1:
                                    viewPhysicalInsuranceList(myPhysicalInsuranceList);
                                    break;
                                case 2:
                                    viewLegalInsuranceList(myLegalInsuranceList);
                                    break;
                                default: break;
                            }
                            pressAnyKey();
                        }
                        break;
                        case 3: {
                            int r, i = 1, num;
                            System.out.println("1 - добавить PHYSICAL_INSURANCE, 2 - добавить LEGAL_INSURANCE, другое - отмена");
                            r = Test.enterInt();
                            switch (r) {
                                case 1:
                                    viewPhysicalInsuranceList(physicalInsuranceList);
                                    System.out.println("Введите номер предложения");
                                    num = checkNumber(physicalInsuranceList.size());

                                    PhysicalInsurance PI = new PhysicalInsurance();
                                    PI.setInsuranceName(physicalInsuranceList.get(num).getInsuranceName());
                                    PI.setInsurant(physicalInsuranceList.get(num).getInsurant());
                                    PI.setTermInMonths(physicalInsuranceList.get(num).getTermInMonths());
                                    PI.setMaxSum(physicalInsuranceList.get(num).getMaxSum());
                                    PI.setRate(physicalInsuranceList.get(num).getRate());
                                    PI.setAge(physicalInsuranceList.get(num).getAge());

                                    myPhysicalInsuranceList.add(PI);
                                    myPhysicalInsuranceList.get(myPhysicalInsuranceList.size() - 1).setNumber(myPhysicalInsuranceList.size());


                                    pressAnyKey();
                                    break;
                                case 2:
                                    viewLegalInsuranceList(legalInsuranceList);
                                    System.out.println("Введите номер предложения");
                                    num = checkNumber(legalInsuranceList.size());

                                    LegalInsurance LI = new LegalInsurance();
                                    LI.setInsuranceName(legalInsuranceList.get(num).getInsuranceName());
                                    LI.setInsurant(legalInsuranceList.get(num).getInsurant());
                                    LI.setTermInMonths(legalInsuranceList.get(num).getTermInMonths());
                                    LI.setMaxSum(legalInsuranceList.get(num).getMaxSum());
                                    LI.setRate(legalInsuranceList.get(num).getRate());
                                    LI.setLegalEntityType(legalInsuranceList.get(num).getLegalEntityType());

                                    myLegalInsuranceList.add(LI);
                                    myLegalInsuranceList.get(myLegalInsuranceList.size() - 1).setNumber(myLegalInsuranceList.size());


                                    pressAnyKey();
                                    break;
                                default: break;
                            }
                        }
                        break;
                        case 4: {
                            int r, i = 1, num;
                            System.out.println("1 - погасить PHYSICAL_INSURANCE, 2 - погасить LEGAL_INSURANCE, другое - отмена");
                            r = Test.enterInt();
                            switch (r) {
                                case 1:

                                    myPhysicalInsuranceList = deletePI(myPhysicalInsuranceList);



                                    pressAnyKey();
                                    break;
                                case 2:

                                    myLegalInsuranceList = deleteLI(myLegalInsuranceList);


                                    pressAnyKey();
                                    break;
                                default: break;
                            }
                        }
                        break;
                        case 5: {
                            int r, i = 1, count, num;
                            System.out.println("1 - продлить PHYSICAL_INSURANCE, 2 - продлить LEGAL_INSURANCE, другое - отмена");
                            r = Test.enterInt();
                            switch (r) {
                                case 1:
                                    viewPhysicalInsuranceList(myPhysicalInsuranceList);
                                    System.out.println("Введите номер продляемого договора");
                                    num = checkNumber(myPhysicalInsuranceList.size());
                                    System.out.println("Введите на сколько месяцев вы хотите его продлить");
                                    count = Test.enterInt();
                                    count += myPhysicalInsuranceList.get(num).getTermInMonths();
                                    myPhysicalInsuranceList.get(num).setTermInMonths(count);


                                    pressAnyKey();
                                    break;
                                case 2:
                                    viewLegalInsuranceList(myLegalInsuranceList);
                                    System.out.println("Введите номер продляемого договора");
                                    num = checkNumber(myLegalInsuranceList.size());
                                    System.out.println("Введите на сколько месяцев вы хотите его продлить");
                                    count = Test.enterInt();
                                    count += myLegalInsuranceList.get(num).getTermInMonths();
                                    myLegalInsuranceList.get(num).setTermInMonths(count);


                                    pressAnyKey();
                                default: break;
                            }
                        }
                        break;
                        case 6: {
                            Save(myPhysicalInsuranceList, myLegalInsuranceList, physicalInsuranceList, legalInsuranceList);
                            check_2 = false;
                        }
                        break;
                        default: {
                            System.out.println("\n\nВы не выбрали функцию, попробуйте снова");
                            pressAnyKey();
                            break;
                        }
                    }

                } while (check_2 == true);
            } else check_1 = false;
        } while (check_1 == true);
    }
}