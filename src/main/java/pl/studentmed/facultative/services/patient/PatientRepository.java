package pl.studentmed.facultative.services.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.studentmed.facultative.models.patient.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {



}
