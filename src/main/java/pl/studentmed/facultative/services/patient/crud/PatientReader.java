package pl.studentmed.facultative.services.patient.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.exceptions.EntityNotFoundException;
import pl.studentmed.facultative.models.patient.Patient;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Patient> getAllPatients(Integer givenOffset, Integer givenLimit) {
        var pageable = createPageRequest(givenOffset, givenLimit);
        return repository.findAll(pageable).stream().collect(Collectors.toList());
    }

    private static PageRequest createPageRequest(Integer givenOffset, Integer givenLimit) {
        var offset = givenOffset != null ? givenOffset : 0;
        var limit = givenLimit != null ? givenLimit : 5;
        return PageRequest.of(offset, limit);
    }

}
