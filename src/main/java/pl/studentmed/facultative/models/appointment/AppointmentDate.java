package pl.studentmed.facultative.models.appointment;

import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;
import pl.studentmed.facultative.exceptions.EmptyFieldException;

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
        this.date = appointmentDate.format(DAY_MONTH_YEAR_TIME);
    }

}
