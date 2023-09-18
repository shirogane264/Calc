import java.util.Scanner;

class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите операцию:");
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        int operand1;
        int operand2;
        String operator;
        String result;
        boolean isRoman;
        String[] expression = input.split(" ");
        if (expression.length != 3) throw new Exception("Должно быть два операнда и один пробел до и после оператора");
        operator = detectOperator(expression);

        //если оба числа римские
        if (Conversion.isRoman(expression[0]) && Conversion.isRoman(expression[2])) {
            operand1 = Conversion.convertToArabian(expression[0]);
            operand2 = Conversion.convertToArabian(expression[2]);
            isRoman = true;
        }
        //если оба числа арабские
        else if (!Conversion.isRoman(expression[0]) && !Conversion.isRoman(expression[2])) {
            operand1 = Integer.parseInt(expression[0]);
            operand2 = Integer.parseInt(expression[2]);
            isRoman = false;
        } else {
            throw new Exception("Числа должны быть в одном формате");
        }


        if (operand1 < 1 || operand1 > 10 || operand2 < 1 || operand2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }

        int arabian = calc(operand1, operand2, operator);

        if (isRoman) {
            if (arabian < 1) {
                throw new Exception("Римское число должно быть больше нуля");
            }
            result = Conversion.convertToRoman(arabian);

        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }

    static String detectOperator(String[] oper) throws Exception {
        switch (oper[1]) {
            case "+" : return "+";
            case "-" : return "-";
            case "*" : return "*";
            case "/" : return "/";
            default : throw new Exception("Неподдерживаемая математическая операция");
        }
    }

    static int calc(int a, int b, String operator) {
        switch (operator) {
            case "+" : return a + b;
            case "-" : return a - b;
            case "*" : return a * b;
            default : return a / b;
        }
    }

    class Conversion {
        static String[] romanArray = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
                "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
                "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
                "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
                "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
                "XCVIII", "XCIX", "C"};


        public static boolean isRoman(String val) {
            for (int i = 0; i < romanArray.length; i++) {
                if (val.equals(romanArray[i])) {
                    return true;
                }
            }
            return false;
        }

        public static int convertToArabian(String roman) {
            for (int i = 0; i < romanArray.length; i++) {
                if (roman.equals(romanArray[i])) {
                    return i;
                }
            }
            return -1;
        }

        public static String convertToRoman(int arabian) {
            return romanArray[arabian];
        }

    }}