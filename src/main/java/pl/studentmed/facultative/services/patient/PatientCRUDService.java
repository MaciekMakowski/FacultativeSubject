package pl.studentmed.facultative.services.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.studentmed.facultative.models.patient.Patient;
import pl.studentmed.facultative.models.user_info.UserInfo;

@Service
@RequiredArgsConstructor
public class PatientCRUDService {

    private final PatientReader reader;
    private final PatientCreator creator;

    public Patient getPatientById(Long patientId) {
        return reader.getPatientById(patientId);
    }

    public Patient createPatient(UserInfo userInfo) {
       return creator.createPatient(userInfo);
    }
}
