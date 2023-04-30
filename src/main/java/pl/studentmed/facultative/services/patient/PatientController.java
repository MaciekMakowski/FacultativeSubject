package pl.studentmed.facultative.services.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.studentmed.facultative.models.patient.Patient;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
class PatientController {

    private final PatientFacade patientFacade;

    @GetMapping("/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getPatientById(@PathVariable Long patientId) {
        return patientFacade.getPatientById(patientId);
    }

}
