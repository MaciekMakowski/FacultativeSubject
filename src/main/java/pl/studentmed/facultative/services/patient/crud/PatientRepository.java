package pl.studentmed.facultative.services.patient.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import pl.studentmed.facultative.exceptions.EntityNotFoundException;
import pl.studentmed.facultative.models.patient.Patient;

import java.util.List;

@RequiredArgsConstructor
class PatientRepository implements IPatientRepository {

    private final JPAPatientRepository repository;

    public Patient getPatientById(Long patientId) {
        return repository.findById(patientId)
                .orElseThrow(
                        () -> new EntityNotFoundException("patient", "Patient with id: " + patientId + " doesn't exists.")
                );
    }

    public List<Patient> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest)
                .stream()
                .toList();
    }

    public Patient saveAndFlush(Patient patient) {
        return repository.saveAndFlush(patient);
    }

}
