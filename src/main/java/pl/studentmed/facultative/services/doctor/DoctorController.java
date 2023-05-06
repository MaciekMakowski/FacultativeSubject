package pl.studentmed.facultative.services.doctor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.studentmed.facultative.models.appointment.AppointmentResponseDTO;
import pl.studentmed.facultative.models.doctor.Doctor;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
@Validated
class DoctorController {

    private final DoctorFacade doctorFacade;

    @GetMapping("/{doctorId}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor getDoctorById(@PathVariable Long doctorId) {
        return doctorFacade.getDoctorById(doctorId);
    }

    @GetMapping("/{doctorId}/appointments")
    public ResponseEntity<List<AppointmentResponseDTO>> getDoctorAppointments(
            @PathVariable Long doctorId,
            @RequestParam(required = false) LocalDate appointmentDate,
            @RequestParam(required = false) @Min(0) @Max(300) Integer offset,
            @RequestParam(required = false) @Min(1) @Max(30) Integer limit
    ) {
        var appointments = doctorFacade.getDoctorAppointments(doctorId, appointmentDate, offset, limit);
        return appointments.size() > 0 ? ResponseEntity.ok(appointments) : ResponseEntity.noContent().build();
    }

}
