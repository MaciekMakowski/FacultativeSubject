package pl.studentmed.facultative.services.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.appointment.Appointment;
import pl.studentmed.facultative.models.appointment.AppointmentCreateDTO;
import pl.studentmed.facultative.models.appointment.AppointmentDate;
import pl.studentmed.facultative.services.doctor.DoctorCRUDService;
import pl.studentmed.facultative.services.patient.PatientCRUDService;

@Component
@RequiredArgsConstructor
public class AppointmentFacade {

    private final AppointmentCRUDService appointmentCRUDService;
    private final PatientCRUDService patientCRUDService;
    private final DoctorCRUDService doctorCRUDService;

    public Appointment getAppointmentById(Long appointmentId) {
        return appointmentCRUDService.getAppointmentById(appointmentId);
    }

    public Appointment createAppointment(AppointmentCreateDTO dto) {
        var patient = patientCRUDService.getPatientById(dto.patientId());
        var doctor = doctorCRUDService.getDoctorById(dto.doctorId());
        var appointmentDate = new AppointmentDate(dto.appDate());
        return appointmentCRUDService.createAppointment(patient, doctor, appointmentDate, dto.patientSymptoms());
    }

}
