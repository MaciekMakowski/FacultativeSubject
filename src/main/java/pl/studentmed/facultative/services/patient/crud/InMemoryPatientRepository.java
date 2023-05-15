package pl.studentmed.facultative.services.patient.crud;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.studentmed.facultative.models.patient.Patient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class InMemoryPatientRepository implements IPatientRepository {

    Map<Long, Patient> DATABASE = new HashMap<>();
    private Long id = 0L;

    @Override
    public Patient getPatientById(Long patientId) {
        return DATABASE.get(patientId);
    }

    @Override
    public List<Patient> findAll(PageRequest pageRequest) {
        return DATABASE.values()
                .stream()
                .skip(pageRequest.getPageNumber())
                .limit(pageRequest.getPageSize())
                .toList();
    }

    @Override
    public Patient saveAndFlush(Patient patient) {
        if(patient.getId() != null && DATABASE.containsKey(patient.getId())) {
            DATABASE.replace(patient.getId(), patient);
        }
        else {
            patient.setId(++id);
            DATABASE.put(id, patient);
        }
        return patient;
    }

    @Override
    public List<Patient> getAllPatientsByDoctorId(Long doctorId, Pageable pageable) {
        throw new NotYetImplementedException();
    }

}
