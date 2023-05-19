package pl.studentmed.facultative.models.appointment;

public record ClinicStatsDTO(Long numberOfAllAppointments,
                             Long numberOfNew,
                             Long numberOfApproved,
                             Long numberOfCanceled,
                             Long numberOfDone,
                             Long numberOfRescheduled) {

    public static ClinicStatsDTO of(Long numberOfAllAppointments, AppointmentStatsDTO dto) {
        return new ClinicStatsDTO(
                numberOfAllAppointments,
                dto.numberOfNew(),
                dto.numberOfApproved(),
                dto.numberOfCanceled(),
                dto.numberOfDone(),
                dto.numberOfRescheduled());
    }
}
