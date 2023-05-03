package pl.studentmed.facultative.services.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.exceptions.EntityNotFoundException;
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

    public AppointmentResponseDTO getAppointmentById(Long appointmentId) {
        return repository.getAppointmentById(appointmentId)
                .orElseThrow(
                        () -> new EntityNotFoundException("appointment", "Appointment with id: " + appointmentId + " doesn't exists.")
                );
    }

    public boolean checkIfDoctorHasAppointementOnThisDate(Doctor doctor, AppointmentDate appointmentDate) {
        return repository.existsByAppointmentDateAndDoctor(appointmentDate, doctor);
    }

    public List<AppointmentResponseDTO> getDoctorAppointments(Long doctorId, LocalDate date, Integer givenOffset, Integer givenLimit) {
        var offset = givenOffset != null ? givenOffset : 0;
        var limit = givenLimit != null ? givenLimit : 5;
        var pageable = PageRequest.of(offset, limit, Sort.by("appointmentDate").ascending());
        if (date != null) {
            var wantedDate = date.format(DAY_MONTH_YEAR);
            return repository.getAppointmentsByDoctorIdAndAppointmentDateLike(doctorId, wantedDate, pageable);
        }
        return repository.getAppointmentsByDoctorId(doctorId, pageable);
    }

    public List<AppointmentResponseDTO> getPatientAppointments(Long patientId, LocalDate date, Integer givenOffset, Integer givenLimit) {
        var offset = givenOffset != null ? givenOffset : 0;
        var limit = givenLimit != null ? givenLimit : 5;
        var pageable = PageRequest.of(offset, limit, Sort.by("appointmentDate").ascending());
        if (date != null) {
            var wantedDate = date.format(DAY_MONTH_YEAR);
            return repository.getAppointmentsByPatientIdAndAppointmentDateLike(patientId, wantedDate, pageable);
        }
        return repository.getAppointmentsByPatientId(patientId, pageable);
    }

}
