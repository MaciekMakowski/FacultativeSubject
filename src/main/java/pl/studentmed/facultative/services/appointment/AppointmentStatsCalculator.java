package pl.studentmed.facultative.services.appointment;

import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.appointment.*;
import pl.studentmed.facultative.models.doctor.Doctor;
import pl.studentmed.facultative.models.doctor.DoctorAppointmentsStatsDTO;

import java.util.ArrayList;
import java.util.List;

@Component
class AppointmentStatsCalculator {

    public AllAppointmentsStatsDTO countClinicAndDoctorsStats(List<Appointment> appointments, List<Doctor> doctors) {
        var clinicStats = countClinicStats(appointments);
        var doctorsStats = countEachDoctorStats(appointments, doctors);
        return new AllAppointmentsStatsDTO(clinicStats, doctorsStats);
    }

    private ClinicStatsDTO countClinicStats(List<Appointment> appointments) {
        return ClinicStatsDTO.of((long) appointments.size(), countAppointmentsByEachStatus(appointments));
    }

    private List<Appointment> excludeDoctorAppointments(List<Appointment> appointments, Doctor doctor) {
        return appointments.stream()
                .filter(appointment -> appointment.getDoctor().getId().equals(doctor.getId()))
                .toList();
    }

    private List<DoctorAppointmentsStatsDTO> countEachDoctorStats(List<Appointment> appointments, List<Doctor> doctors) {
        ArrayList<DoctorAppointmentsStatsDTO> statsList = new ArrayList<>();
        for (Doctor doctor: doctors) {
            var doctorAppointments = excludeDoctorAppointments(appointments, doctor);
            var doctorAppointmentsStats = countAppointmentsByEachStatus(doctorAppointments);
            statsList.add(DoctorAppointmentsStatsDTO.of(doctor, doctorAppointmentsStats));
        }
        return statsList;
    }

    private Long countAppointmentsBy(AppointmentStatus status, List<Appointment> appointments) {
        return appointments.stream()
                .filter(appointment -> appointment.getStatus().equals(status))
                .count();
    }

    private AppointmentStatsDTO countAppointmentsByEachStatus(List<Appointment> appointments) {
        var numberOfNew = countAppointmentsBy(AppointmentStatus.NEW, appointments);
        var numberOfApproved = countAppointmentsBy(AppointmentStatus.APPROVED, appointments);
        var numberOfCanceled = countAppointmentsBy(AppointmentStatus.CANCELED, appointments);
        var numberOfDone = countAppointmentsBy(AppointmentStatus.DONE, appointments);
        var numberOfRescheduled = countAppointmentsBy(AppointmentStatus.RESCHEDULED, appointments);
        return new AppointmentStatsDTO(numberOfNew, numberOfApproved, numberOfCanceled, numberOfDone, numberOfRescheduled);
    }

}
