package pl.studentmed.facultative.services.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.studentmed.facultative.models.appointment.AppointmentCreateDTO;
import pl.studentmed.facultative.models.appointment.AppointmentResponseDTO;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentFacade facade;

    @GetMapping("/{appointmentId}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponseDTO getAppointmentById(@PathVariable Long appointmentId) {
        return facade.getAppointmentById(appointmentId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponseDTO createAppointment(@RequestBody AppointmentCreateDTO appointmentCreateDTO) {
        return facade.createAppointment(appointmentCreateDTO);
    }

}
