package pl.studentmed.facultative.services.patient.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import pl.studentmed.facultative.models.patient.Patient;

import java.util.List;

@RequiredArgsConstructor
class PatientReader {

    private final IPatientRepository repository;

    public Patient getPatientById(Long patientId) {
        return repository.getPatientById(patientId);
    }

    public List<Patient> getAllPatients(Integer givenOffset, Integer givenLimit) {
        var pageable = createPageRequest(givenOffset, givenLimit);
        return repository.findAll(pageable);
    }

    private static PageRequest createPageRequest(Integer givenOffset, Integer givenLimit) {
        var offset = givenOffset != null ? givenOffset : 0;
        var limit = givenLimit != null ? givenLimit : 5;
        return PageRequest.of(offset, limit);
    }

}
