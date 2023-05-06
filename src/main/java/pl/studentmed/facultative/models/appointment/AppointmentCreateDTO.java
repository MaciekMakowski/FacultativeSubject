package pl.studentmed.facultative.models.appointment;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentCreateDTO(
                                   @NotNull Long patientId,
                                   @NotNull Long doctorId,
                                   @NotNull
                                   @Future
                                   LocalDateTime appointmentDate,
                                   String patientSymptoms) {
}
