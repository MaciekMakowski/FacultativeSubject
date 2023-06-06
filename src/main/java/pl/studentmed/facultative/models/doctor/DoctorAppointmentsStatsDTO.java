package pl.studentmed.facultative.models.doctor;

import pl.studentmed.facultative.models.appointment.AppointmentStatsDTO;

public record DoctorAppointmentsStatsDTO(Long doctorId,
                                         String doctorName,
                                         AppointmentStatsDTO doctorAppointmentsStats) {

    public static DoctorAppointmentsStatsDTO of(Doctor doctor, AppointmentStatsDTO appointmentStats) {
        return new DoctorAppointmentsStatsDTO(doctor.getId(), doctor.getUserInfo().getFullName(), appointmentStats);
    }

}
