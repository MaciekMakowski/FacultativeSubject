package pl.studentmed.facultative.services.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.studentmed.facultative.models.patient.Patient;

@Service
@RequiredArgsConstructor
public class PatientCRUDService {

    private final PatientReader reader;

    public Patient getPatientById(Long patientId) {
        return reader.getPatientById(patientId);
    }

}
