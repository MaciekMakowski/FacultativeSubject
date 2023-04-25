package pl.studentmed.facultative.models.appointment;

import java.time.LocalDateTime;

public record AppointmentCreateDTO(Long patientId, Long doctorId,
                                   LocalDateTime appDate, String patientSymptoms) {
}
