package pl.studentmed.facultative.models.appointment;

import java.util.List;

public record AppointmentsHoursDTO(String hour, List<AvailableHoursDTO> hours) {
}
