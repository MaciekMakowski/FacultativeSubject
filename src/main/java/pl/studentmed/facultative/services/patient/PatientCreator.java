package pl.studentmed.facultative.services.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.patient.Patient;
import pl.studentmed.facultative.models.user_info.UserInfo;

@Component
@RequiredArgsConstructor
class PatientCreator {

    private final PatientRepository repository;

    public Patient createPatient(UserInfo userInfo) {
        var patient = new Patient(userInfo);
        return repository.saveAndFlush(patient);
    }

}
