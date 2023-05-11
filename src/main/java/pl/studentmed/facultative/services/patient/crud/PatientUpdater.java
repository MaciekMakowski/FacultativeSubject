package pl.studentmed.facultative.services.patient.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.patient.Patient;

@Component
@RequiredArgsConstructor
class PatientUpdater {

    private final PatientRepository repository;

    public Patient updatePatient(Patient patient, String allergies, String medicines) {
        if (allergies != null) {
            patient.setAllergies(allergies);
        }
        if (medicines != null) {
            patient.setMedicines(medicines);
        }
        return repository.saveAndFlush(patient);
    }

}
