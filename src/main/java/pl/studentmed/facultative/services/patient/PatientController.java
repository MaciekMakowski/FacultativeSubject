package pl.studentmed.facultative.services.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.studentmed.facultative.models.appointment.AppointmentResponseDTO;
import pl.studentmed.facultative.models.patient.PatientResponseDTO;
import pl.studentmed.facultative.models.patient.PatientUpdateDTO;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    public PatientResponseDTO getPatientById(@PathVariable Long patientId) {
        return patientFacade.getPatientById(patientId);
    }

    @GetMapping("/{patientId}/appointments")
    public ResponseEntity<List<AppointmentResponseDTO>> getPatientAppointments(
            @PathVariable Long patientId,
            @RequestParam String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate appointmentDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate secondDate,
            @RequestParam(required = false) @Min(0) @Max(300) Integer offset,
            @RequestParam(required = false) @Min(1) @Max(30) Integer limit
            ) {
        var appointments = patientFacade.getPatientAppointments(patientId, status, appointmentDate, secondDate, offset, limit);
        return appointments.size() > 0 ? ResponseEntity.ok(appointments) : ResponseEntity.noContent().build();
    }

    @PatchMapping("")
    @ResponseStatus(HttpStatus.OK)
    public PatientResponseDTO updatePatient(@Valid @RequestBody PatientUpdateDTO patientUpdateDTO) {
        return patientFacade.updatePatient(patientUpdateDTO);
    }

}
