package ua.com.foxminded.accountingsystem.util;

public class GeneratorUtil {

    public static String generateStringWithLength(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append("c");
        }
        return builder.toString();
    }

    public static String generateCorrectEmail() {
        return "info@foxminded.com.ua";
    }

    public static String generateIncorrectEmail() {
        return "IncorrectEmail.Foxminded.com.ua";
    }
}
