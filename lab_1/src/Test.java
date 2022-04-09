import java.util.Scanner;

class Test {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static int enterInt() throws NoIntException {
        Scanner input = new Scanner(System.in);
        String t = input.next();
        boolean hasInt = true;
        int x = 0;
        try {
            for (char c : t.toCharArray()) {
                if (!Character.isDigit(c)) {
                    hasInt = false;
                }
            }
            if (hasInt) {
                x = Integer.parseInt(t);
            } else {
                throw new NoIntException(ANSI_RED + "Введите целое число!" + ANSI_RESET);
            }
        } catch (NoIntException e) {
            System.out.println(e.getMessage());
            x = enterInt();
        }
        return x;
    }

    public static double enterDouble() throws NoDoubleException {
        Scanner input = new Scanner(System.in);
        String t = input.next();
        boolean hasDouble = true;
        int point = 0;
        double x = 0;
        try {
            for (char c : t.toCharArray()) {
                if (!Character.isDigit(c)) {
                    if (c == '.') {
                        point++;
                        if (point > 1) {
                            hasDouble = false;
                        }
                    } else {
                        hasDouble = false;
                    }
                }
            }
            if (hasDouble) {
                x = Double.parseDouble(t);
            } else {
                throw new NoDoubleException(ANSI_RED + "Введите число!" + ANSI_RESET);
            }
        } catch (NoDoubleException e) {
            System.out.println(e.getMessage());
            x = enterDouble();
        }
        return x;
    }
}

