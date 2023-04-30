package pl.studentmed.facultative.models;

import java.time.format.DateTimeFormatter;

public class StudentMedDateUtils {

    public final static DateTimeFormatter DAY_MONTH_YEAR_TIME = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    public final static DateTimeFormatter DAY_MONTH_YEAR = DateTimeFormatter.ofPattern("dd.MM.yyyy");

}
