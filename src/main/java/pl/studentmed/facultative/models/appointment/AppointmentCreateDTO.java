package pl.studentmed.facultative.models.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentCreateDTO(
                                   @NotNull Long patientId,
                                   @NotNull Long doctorId,
                                   @NotNull
                                   @Future
                                   LocalDateTime appointmentDate,
                                   String patientSymptoms) {
}
