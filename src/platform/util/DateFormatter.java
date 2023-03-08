package platform.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateFormatter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private DateFormatter() {
    }

    public static String formatWithPattern(LocalDateTime date) {
        return date.format(formatter);
    }

}
