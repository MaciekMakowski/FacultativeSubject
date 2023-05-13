package pl.studentmed.facultative.services.patient.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.studentmed.facultative.models.patient.Patient;
import pl.studentmed.facultative.models.patient.PatientUpdateDTO;
import pl.studentmed.facultative.models.user_info.UserInfo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientCRUDService {

    private final PatientReader reader;
    private final PatientCreator creator;
    private final PatientUpdater updater;

    public Patient getPatientById(Long patientId) {
        return reader.getPatientById(patientId);
    }

    public List<Patient> getAllPatients(Integer offset, Integer limit) {
        return reader.getAllPatients(offset, limit);
    }

    public Patient createPatient(UserInfo userInfo) {
       return creator.createPatient(userInfo);
    }

    public Patient updatePatient(PatientUpdateDTO patientUpdateDTO) {
        var patient = reader.getPatientById(patientUpdateDTO.patientId());
        return updater.updatePatient(
                patient,
                patientUpdateDTO.allergies(),
                patientUpdateDTO.medicines()
        );
    }

}
