package pl.studentmed.facultative.services.patient.crud;

import org.springframework.data.domain.PageRequest;
import pl.studentmed.facultative.models.patient.Patient;

import java.util.List;

public interface IPatientRepository {
    Patient getPatientById(Long patientId);

    List<Patient> findAll(PageRequest pageRequest);

    Patient saveAndFlush(Patient patient);
}
