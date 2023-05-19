package pl.studentmed.facultative.models.appointment;

public record AppointmentStatsDTO(Long numberOfNew,
                                  Long numberOfApproved,
                                  Long numberOfCanceled,
                                  Long numberOfDone,
                                  Long numberOfRescheduled) {
}
