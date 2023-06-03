package pl.studentmed.facultative.models.appointment;

import javax.validation.constraints.NotNull;

public record AppointmentEditDTO(@NotNull Long appointmentId,
                                 @NotNull Long userInfoId,
                                 String newStatus) {
}
