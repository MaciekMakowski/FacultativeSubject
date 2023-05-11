package pl.studentmed.facultative.services.doctor.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.doctor.Doctor;
import pl.studentmed.facultative.models.doctor.Specialization;

@Component
@RequiredArgsConstructor
class DoctorUpdater {

    private final DoctorRepository repository;

    public Doctor changeDoctorSpecialization(Doctor doctor, String givenSpecialization) {
        var specialization = Specialization.getSpecialization(givenSpecialization);
        doctor.setSpecialization(specialization);
        return repository.saveAndFlush(doctor);
    }

}
