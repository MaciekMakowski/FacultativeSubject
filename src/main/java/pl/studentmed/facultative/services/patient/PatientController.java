package pl.studentmed.facultative.services.patient;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.studentmed.facultative.models.appointment.AppointmentResponseDTO;
import pl.studentmed.facultative.models.patient.Patient;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
@Validated
class PatientController {

    private final PatientFacade patientFacade;

    @GetMapping("/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getPatientById(@PathVariable Long patientId) {
        return patientFacade.getPatientById(patientId);
    }

    @GetMapping("/{patientId}/appointments")
    public ResponseEntity<List<AppointmentResponseDTO>> getPatientAppointments(
            @PathVariable Long patientId,
            @RequestParam(required = false) LocalDate appointmentDate,
            @RequestParam(required = false) @Min(0) @Max(300) Integer offset,
            @RequestParam(required = false) @Min(1) @Max(30) Integer limit
            ) {
        var appointments = patientFacade.getPatientAppointments(patientId, appointmentDate, offset, limit);
        return appointments.size() > 0 ? ResponseEntity.ok(appointments) : ResponseEntity.noContent().build();
    }

}
