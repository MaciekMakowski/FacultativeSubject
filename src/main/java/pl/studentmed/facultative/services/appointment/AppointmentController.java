package pl.studentmed.facultative.services.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.studentmed.facultative.models.appointment.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/appointments")
@Validated
class AppointmentController {

    private final AppointmentFacade facade;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponseDTO> getAllAppointments() {
        return facade.getAllAppointments();
    }

    @GetMapping("/{appointmentId}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponseDTO getAppointmentById(@PathVariable Long appointmentId) {
        return facade.getAppointmentDTOById(appointmentId);
    }

    @GetMapping("/new")
    public List<AppointmentResponseDTO> getAllNewAppointments(
            @RequestParam(required = false) @Min(0) @Max(300) Integer offset,
            @RequestParam(required = false) @Min(1) @Max(30) Integer limit
    ) {
        return facade.getAllNewAppointments(offset, limit);
    }

    @GetMapping("/busy_at")
    public List<AppointmentsHoursDTO> getBusyAppointmentHoursForDateAndDoctorId(
            @NotNull @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate givenDate,
            @NotNull @RequestParam Long doctorId) {
        return facade.getBusyAppointmentHoursForDateAndDoctorId(givenDate, doctorId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponseDTO createAppointment(@Valid @RequestBody AppointmentCreateDTO appointmentCreateDTO) {
        return facade.createAppointment(appointmentCreateDTO);
    }

    @PatchMapping("")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponseDTO editAppointment(@Valid @RequestBody AppointmentEditDTO appointmentEditDTO) {
        return facade.editAppointment(appointmentEditDTO);
    }

    @PatchMapping("/done/{appointmentId}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponseDTO finnishAppointment(@PathVariable Long appointmentId,
                                                     @RequestParam(required = false) String recommendations) {
        return facade.finnishAppointment(appointmentId, recommendations);
    }

    @GetMapping("/clinic-stats")
    @ResponseStatus(HttpStatus.OK)
    public AllAppointmentsStatsDTO getClinicStats() {
        return facade.getClinicAndDoctorsStats();
    }

}
