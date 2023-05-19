package pl.studentmed.facultative.models.appointment;

import pl.studentmed.facultative.models.doctor.DoctorAppointmentsStatsDTO;

import java.util.List;

public record AllAppointmentsStatsDTO(ClinicStatsDTO clinicStats,
                                      List<DoctorAppointmentsStatsDTO> doctorsStats) {
}
