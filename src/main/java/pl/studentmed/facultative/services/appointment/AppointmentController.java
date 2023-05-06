package pl.studentmed.facultative.services.appointment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.studentmed.facultative.models.appointment.AppointmentBusyHoursDTO;
import pl.studentmed.facultative.models.appointment.AppointmentCreateDTO;
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
    public ResponseEntity<List<AppointmentBusyHoursDTO>> getBusyAppointmentHoursForDate(@NotNull @RequestParam LocalDate givenDate) {
        var busyHours = facade.getBusyAppointmentHoursForDate(givenDate);
        return busyHours.size() > 0 ? ResponseEntity.ok(busyHours) : ResponseEntity.noContent().build();
    }

}
