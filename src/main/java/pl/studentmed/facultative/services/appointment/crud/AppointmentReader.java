package pl.studentmed.facultative.services.appointment.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.exceptions.EntityNotFoundException;
import pl.studentmed.facultative.exceptions.NoFreeDoctorAppointmentsException;
import pl.studentmed.facultative.models.appointment.*;
import pl.studentmed.facultative.models.doctor.Doctor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static pl.studentmed.facultative.models.StudentMedDateUtils.DAY_MONTH_YEAR;
import static pl.studentmed.facultative.models.StudentMedDateUtils.SELECTABLE_HOURS;

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

    public boolean checkIfDoctorHasAppointmentOnThisDate(Doctor doctor, AppointmentDate appointmentDate) {
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

    public List<AppointmentResponseDTO> getPatientAppointments(Long patientId, AppointmentStatus status, LocalDate appointmentDate,
                                                               LocalDate secondDate, Integer givenOffset,
                                                               Integer givenLimit) {
        var pageable = createPageRequest(givenOffset, givenLimit);
        if (appointmentDate != null && secondDate != null && status != null) {
            return getAppointmentWithDateBetween(patientId, status, appointmentDate, secondDate, pageable);
        }
        if (appointmentDate != null && status != null) {
            return getAppointmentWithDateLike(patientId, status, appointmentDate, pageable);
        }
        if (status != null) {
            return repository.getAppointmentsByPatientIdAndStatus(patientId, status, pageable);
        }
        return repository.getAppointmentsByPatientId(patientId, pageable);
    }

    private List<AppointmentResponseDTO> getAppointmentWithDateBetween(Long patientId, AppointmentStatus status,
                                                                       LocalDate firstDate, LocalDate secondDate,
                                                                       PageRequest pageable) {
        var startDate = firstDate.format(DAY_MONTH_YEAR);
        var endDate = secondDate.format(DAY_MONTH_YEAR);
        return repository.getAppointmentsByPatientIdAndDateBetween(patientId, status, startDate, endDate, pageable);
    }
    private List<AppointmentResponseDTO> getAppointmentWithDateLike(Long patientId, AppointmentStatus status,
                                                                    LocalDate appointmentDate, PageRequest pageable) {
        var wantedDate = appointmentDate.format(DAY_MONTH_YEAR);
        return repository.getAppointmentsByPatientIdAndAppointmentDateLike(patientId, status, wantedDate, pageable);
    }

    public List<AppointmentResponseDTO> getAllNewAppointments(Integer givenOffset, Integer givenLimit) {
        var pageable = createPageRequest(givenOffset, givenLimit);
        return repository.getAllNewAppointments(pageable);
    }

    private static PageRequest createPageRequest(Integer givenOffset, Integer givenLimit) {
        var offset = givenOffset != null ? givenOffset : 0;
        var limit = givenLimit != null ? givenLimit : 5;
        return PageRequest.of(offset, limit, Sort.by("appointmentDate").ascending());
    }

    public List<String> getBusyAppointmentHoursForDateAndDoctorId(LocalDate givenDate, Long doctorId) {
        var wantedDate = givenDate.format(DAY_MONTH_YEAR);
        return repository.getBusyAppointmentsHoursByGivenDateAndDoctorId(wantedDate, doctorId);
    }

    public AppointmentDate getControlAppointmentDate(Appointment appointment) {
        int maxDaysToCheck = 365;
        int daysToAdd = 0;
        // TODO : exclude saturdays and sundays
        while (daysToAdd <= maxDaysToCheck) {
            var appointmentDateAfterDays = appointment.getAppointmentDate().toLocalDateTime().plusDays(14 + daysToAdd);
            var appointmentDateFormatted = appointmentDateAfterDays.toLocalDate().format(DAY_MONTH_YEAR);
            var appointmentTimes = repository.getBusyAppointmentsHoursByGivenDateAndDoctorId(appointmentDateFormatted, appointment.getDoctor().getId());

            if (appointmentTimes == null || appointmentTimes.isEmpty()) {
                return createAppointmentDate(appointmentDateFormatted, SELECTABLE_HOURS.get(0));
            }

            String availableTime = findAvailableTime(appointmentTimes);
            if (availableTime != null) {
                return createAppointmentDate(appointmentDateFormatted, availableTime);
            }

            daysToAdd++;
        }
        throw new NoFreeDoctorAppointmentsException("appointmentDate", "There is no free date in the next "
                + maxDaysToCheck + " days to make control appointment with the same doctor.");
    }

    private LocalDateTime toLocalDateTime(String date, String time) {
        var days = date.substring(0, 2);
        var month = date.substring(3, 5);
        var year = date.substring(6, 10);
        var hours = time.substring(0, 2);
        var minutes = time.substring(3, 5);
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(days),
                Integer.parseInt(hours), Integer.parseInt(minutes));
    }

    private AppointmentDate createAppointmentDate(String appointmentDate, String appointmentTime) {
        LocalDateTime dateTime = toLocalDateTime(appointmentDate, appointmentTime);
        return new AppointmentDate(dateTime);
    }

    private String findAvailableTime(List<String> appointmentTimes) {
        for (String time : SELECTABLE_HOURS) {
            if (!appointmentTimes.contains(time)) {
                return time;
            }
        }
        return null;
    }

    public List<Appointment> getAllAppointmentEntities() {
        return repository.findAll();
    }

}
