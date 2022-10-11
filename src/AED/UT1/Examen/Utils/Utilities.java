package AED.UT1.Examen.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

    private Utilities(){}

    public static double randomNum(boolean doubleValue,int minvalue,int maxValue){
        Random random = new Random();

        if (doubleValue){
            return random.nextDouble(minvalue,maxValue);
        }else {
            return random.nextInt(minvalue,maxValue);
        }
    }

    public static boolean DniCheck(String dni) {
        String stringPattern = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$";

        if (PatternCompiler(stringPattern, dni) && CheckDniLetter(dni)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean CheckDniLetter(String dni) {
        String characters = "TRWAGMYFPDXBNJZSQVHLCKE";
        int dniNumbers = Integer.parseInt(dni.substring(0, 8));

        char dniCharacter = characters.charAt(dniNumbers % 23);

        if (dniCharacter == dni.charAt(8)) return true;
        else return false;
    }

    public static boolean DateCheck(String date) {
        String stringPattern = "^(\\d{4})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";

        return PatternCompiler(stringPattern, date);
    }

    public static boolean CheckIfNull(String data) {
        if (data == null) return true;
        if (data.equals("")) return true;

        return false;
    }

    public static boolean NegativeNumberCheck(String number) {
        double num;

        try {
            num = Double.parseDouble(number);

            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean onlyDigitCheck(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean onlyDoubleCheck(String text) {
        try {
            Double.parseDouble(text);
        }catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static boolean onlyStringCheck(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static String DoOperationWithDate(String date, int difference) throws ParseException { //Se puede con dias, meses, etc
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date newDate;
        newDate = dateFormat.parse(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(newDate);
        calendar.add(Calendar.DATE, difference); //+difference (if 5, YEAR+5)

        newDate = calendar.getTime();

        return dateFormat.format(newDate);
    }

    public static String GetSystemDate() {
        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return formatter.format(date);
    }

    private static boolean PatternCompiler(String stringPattern, String input) {
        Pattern pattern = Pattern.compile(stringPattern, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
