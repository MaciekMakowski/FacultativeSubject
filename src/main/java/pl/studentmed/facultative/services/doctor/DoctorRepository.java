package pl.studentmed.facultative.services.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.studentmed.facultative.models.doctor.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {



}
