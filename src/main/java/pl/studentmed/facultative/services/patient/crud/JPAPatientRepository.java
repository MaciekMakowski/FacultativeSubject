package pl.studentmed.facultative.services.patient.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.studentmed.facultative.models.patient.Patient;

interface JPAPatientRepository extends JpaRepository<Patient, Long> {
}
