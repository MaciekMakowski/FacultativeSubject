package pl.studentmed.facultative.services.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.patient.Patient;

@Component
@RequiredArgsConstructor
class PatientFacade {

    private final PatientCRUDService patientCRUDService;

    public Patient getPatientById(Long patientId) {
        return patientCRUDService.getPatientById(patientId);
    }

}
