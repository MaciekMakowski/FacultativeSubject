package pl.studentmed.facultative.services.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.doctor.Doctor;

@Component
@RequiredArgsConstructor
class DoctorUpdater {

    private final DoctorRepository repository;

    public Doctor changeDoctorSpecialization(Doctor doctor, String specialization) {
        doctor.setSpecialization(specialization);
        return repository.saveAndFlush(doctor);
    }

}
