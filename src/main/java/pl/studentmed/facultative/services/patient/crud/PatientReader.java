package pl.studentmed.facultative.services.patient.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.exceptions.EntityNotFoundException;
import pl.studentmed.facultative.models.patient.Patient;

@Component
@RequiredArgsConstructor
class PatientReader {

    private final PatientRepository repository;

    public Patient getPatientById(Long patientId) {
        return repository.findById(patientId)
                .orElseThrow(
                        () -> new EntityNotFoundException("patient", "Patient with id: " + patientId + " doesn't exists.")
                );
    }

}
