package pl.studentmed.facultative.models;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class StudentMedDateUtils {

    public final static DateTimeFormatter DAY_MONTH_YEAR_TIME = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    public final static DateTimeFormatter DAY_MONTH_YEAR = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public final static List<String> SELECTABLE_HOURS = Arrays.asList("09:00", "09:35", "10:10", "10:45", "11:20", "12:00", "12:35",
            "13:10", "13:45", "14:40", "15:15", "15:50", "16:25", "17:00");

}
