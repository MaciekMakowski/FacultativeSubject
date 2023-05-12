package pl.studentmed.facultative.models.appointment;

import lombok.NoArgsConstructor;
import pl.studentmed.facultative.exceptions.AppointmentDateTooLateException;
import pl.studentmed.facultative.exceptions.EmptyFieldException;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

import static pl.studentmed.facultative.models.StudentMedDateUtils.DAY_MONTH_YEAR_TIME;

@Embeddable
@NoArgsConstructor
public class AppointmentDate {

    public String date;

    // how example appointment date should look: 25.04.2023 15:00
    public AppointmentDate(LocalDateTime appointmentDate) {
        if (appointmentDate == null) {
            throw new EmptyFieldException("appointmentDate", "Appointment date can't be empty.");
        }
        if (appointmentDate.isBefore(LocalDateTime.now().plusMinutes(15))) {
            throw new AppointmentDateTooLateException("appointmentDate", "Appointment must be made at least 15 minutes before it.");
        }
        this.date = appointmentDate.format(DAY_MONTH_YEAR_TIME);
    }

    public LocalDateTime toLocalDateTime() {
        var days = this.date.substring(0, 2);
        var month = this.date.substring(3, 5);
        var year = this.date.substring(6, 10);
        var hours = this.date.substring(11, 13);
        var minutes = this.date.substring(14);
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(days),
                Integer.parseInt(hours), Integer.parseInt(minutes));
    }

}
