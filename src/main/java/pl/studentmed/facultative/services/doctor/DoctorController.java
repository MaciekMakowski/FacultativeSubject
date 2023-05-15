package pl.studentmed.facultative.services.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.studentmed.facultative.models.appointment.AppointmentResponseDTO;
import pl.studentmed.facultative.models.doctor.DoctorSpecializationDTO;
import pl.studentmed.facultative.models.doctor.DoctorUserInfoDTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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
    public DoctorUserInfoDTO getDoctorById(@PathVariable Long doctorId) {
        return doctorFacade.getDoctorById(doctorId);
    }

    @GetMapping("/{doctorId}/appointments")
    public ResponseEntity<List<AppointmentResponseDTO>> getDoctorAppointments(
            @PathVariable Long doctorId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate appointmentDate,
            @RequestParam(required = false) @Min(0) @Max(300) Integer offset,
            @RequestParam(required = false) @Min(1) @Max(30) Integer limit
    ) {
        var appointments = doctorFacade.getDoctorAppointments(doctorId, appointmentDate, offset, limit);
        return appointments.size() > 0 ? ResponseEntity.ok(appointments) : ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<List<DoctorSpecializationDTO>> getAllDoctors() {
        var doctors = doctorFacade.getAllDoctors();
        return doctors.size() > 0 ? ResponseEntity.ok(doctors) : ResponseEntity.noContent().build();
    }

    @GetMapping("by-specialization")
    public ResponseEntity<List<DoctorSpecializationDTO>> getDoctorsBySpecialization(@NotEmpty @RequestParam String specialization) {
        var doctors = doctorFacade.getDoctorsBySpecialization(specialization);
        return doctors.size() > 0 ? ResponseEntity.ok(doctors) : ResponseEntity.noContent().build();
    }

    @PatchMapping("/{doctorId}")
    public DoctorSpecializationDTO changeDoctorSpecialization(@PathVariable Long doctorId, @NotEmpty @RequestParam String specialization) {
        return doctorFacade.changeDoctorSpecialization(doctorId, specialization);
    }

}
