package pl.studentmed.facultative.services.doctor.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.doctor.Doctor;
import pl.studentmed.facultative.models.doctor.Specialization;

@Component
@RequiredArgsConstructor
class DoctorUpdater {

    private final DoctorRepository repository;

    public Doctor editDoctor(Doctor doctor, String specialization, String description, String photo) {
        if (specialization != null) {
            var newSpecialization = Specialization.getSpecialization(specialization);
            doctor.setSpecialization(newSpecialization);
        }
        if (description != null) {
            doctor.setDescription(description);
        }
        if (photo != null) {
            doctor.setPhoto(photo);
        }
        return repository.saveAndFlush(doctor);
    }

}
