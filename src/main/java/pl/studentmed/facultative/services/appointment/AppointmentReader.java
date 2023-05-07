package pl.studentmed.facultative.services.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.exceptions.EntityNotFoundException;
import pl.studentmed.facultative.models.appointment.Appointment;
import pl.studentmed.facultative.models.appointment.AppointmentBusyHoursDTO;
import pl.studentmed.facultative.models.appointment.AppointmentDate;
import pl.studentmed.facultative.models.appointment.AppointmentResponseDTO;
import pl.studentmed.facultative.models.doctor.Doctor;

import java.time.LocalDate;
import java.util.List;

import static pl.studentmed.facultative.models.StudentMedDateUtils.DAY_MONTH_YEAR;

@Component
@RequiredArgsConstructor
class AppointmentReader {

    private final AppointmentRepository repository;

    public AppointmentResponseDTO getAppointmentDTOById(Long appointmentId) {
        return repository.getAppointmentById(appointmentId)
                .orElseThrow(
                        () -> new EntityNotFoundException("appointment", "Appointment with id: " + appointmentId + " doesn't exists.")
                );
    }

    public Appointment getAppointmentById(Long appointmentId) {
        return repository.findById(appointmentId)
                .orElseThrow(
                        () -> new EntityNotFoundException("appointment", "Appointment with id: " + appointmentId + " doesn't exists.")
                );
    }

    public boolean checkIfDoctorHasAppointementOnThisDate(Doctor doctor, AppointmentDate appointmentDate) {
        return repository.existsByAppointmentDateAndDoctor(appointmentDate, doctor);
    }

    public List<AppointmentResponseDTO> getDoctorAppointments(Long doctorId, LocalDate date, Integer givenOffset, Integer givenLimit) {
        var pageable = createPageRequest(givenOffset, givenLimit);
        if (date != null) {
            var wantedDate = date.format(DAY_MONTH_YEAR);
            return repository.getAppointmentsByDoctorIdAndAppointmentDateLike(doctorId, wantedDate, pageable);
        }
        return repository.getAppointmentsByDoctorId(doctorId, pageable);
    }

    public List<AppointmentResponseDTO> getPatientAppointments(Long patientId, LocalDate appointmentDate,
                                                               LocalDate secondDate, Integer givenOffset,
                                                               Integer givenLimit) {
        var pageable = createPageRequest(givenOffset, givenLimit);
        if (appointmentDate != null && secondDate != null) {
            return getAppointmentWithDateBetween(patientId, appointmentDate, secondDate, pageable);
        }
        if (appointmentDate != null) {
            return getAppointmentWithDateLike(patientId, appointmentDate, pageable);
        }
        return repository.getAppointmentsByPatientId(patientId, pageable);
    }

    private List<AppointmentResponseDTO> getAppointmentWithDateBetween(
            Long patientId, LocalDate firstDate, LocalDate secondDate, PageRequest pageable) {
        var startDate = firstDate.format(DAY_MONTH_YEAR);
        var endDate = secondDate.format(DAY_MONTH_YEAR);
        return repository.getAppointmentsByPatientIdAndDateBetween(patientId, startDate, endDate, pageable);
    }
    private List<AppointmentResponseDTO> getAppointmentWithDateLike(
            Long patientId, LocalDate appointmentDate, PageRequest pageable) {
        var wantedDate = appointmentDate.format(DAY_MONTH_YEAR);
        return repository.getAppointmentsByPatientIdAndAppointmentDateLike(patientId, wantedDate, pageable);
    }

    private static PageRequest createPageRequest(Integer givenOffset, Integer givenLimit) {
        var offset = givenOffset != null ? givenOffset : 0;
        var limit = givenLimit != null ? givenLimit : 5;
        return PageRequest.of(offset, limit, Sort.by("appointmentDate").ascending());
    }

    public List<AppointmentBusyHoursDTO> getBusyAppointmentHoursForDate(LocalDate givenDate) {
        var wantedDate = givenDate.format(DAY_MONTH_YEAR);
        return repository.getBusyAppointmentsHoursByGivenDate(wantedDate);
    }

}
