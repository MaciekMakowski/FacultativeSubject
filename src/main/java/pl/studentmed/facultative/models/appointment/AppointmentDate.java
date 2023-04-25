package pl.studentmed.facultative.models.appointment;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
public class AppointmentDate {

    public String date;

    public AppointmentDate(String date, String time) {
        // todo: validation, probably using LocalDateTime.of() method will be enough to check
        // todo: if date is valid
        var properDate = LocalDateTime.of(getYear(date), getMonth(date), getDay(date), getHour(time), getMinutes(time));
        this.date = date + " " + time;
    }

    // example date: 25.04.2023 15:00
    private int getYear(String date) {
        return Integer.parseInt(date.substring(6));
    }

    private int getMonth(String date) {
        return Integer.parseInt(date.substring(3, 5));
    }

    private int getDay(String date) {
        return Integer.parseInt(date.substring(0, 2));
    }

    private int getHour(String time) {
        return Integer.parseInt(time.substring(0, 2));
    }

    private int getMinutes(String time) {
        return Integer.parseInt(time.substring(3));
    }

}
