package pl.studentmed.facultative.services.appointment;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.studentmed.facultative.models.appointment.AppointmentBusyHoursDTO;
import pl.studentmed.facultative.models.appointment.AppointmentCreateDTO;
import pl.studentmed.facultative.models.appointment.AppointmentEditDTO;
import pl.studentmed.facultative.models.appointment.AppointmentResponseDTO;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/appointments")
@Validated
class AppointmentController {

    private final AppointmentFacade facade;

    @GetMapping("/{appointmentId}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponseDTO getAppointmentById(@PathVariable Long appointmentId) {
        return facade.getAppointmentDTOById(appointmentId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponseDTO createAppointment(@Valid @RequestBody AppointmentCreateDTO appointmentCreateDTO) {
        return facade.createAppointment(appointmentCreateDTO);
    }

    @GetMapping("/busy_at")
    public ResponseEntity<List<AppointmentBusyHoursDTO>> getBusyAppointmentHoursForDateAndDoctorId(
            @NotNull @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate givenDate,
            @NotNull @RequestParam Long doctorId) {
        var busyHours = facade.getBusyAppointmentHoursForDateAndDoctorId(givenDate, doctorId);
        return busyHours.size() > 0 ? ResponseEntity.ok(busyHours) : ResponseEntity.noContent().build();
    }

    @PatchMapping("")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponseDTO editAppointment(@Valid @RequestBody AppointmentEditDTO appointmentEditDTO) {
        return facade.editAppointment(appointmentEditDTO);
    }

}
