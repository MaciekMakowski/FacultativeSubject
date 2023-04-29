package pl.studentmed.facultative.services.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.studentmed.facultative.models.doctor.Doctor;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
class DoctorController {

    private final DoctorFacade doctorFacade;

    @GetMapping("/{doctorId}")
    public Doctor getDoctorById(@PathVariable Long doctorId) {
        return doctorFacade.getDoctorById(doctorId);
    }

}
