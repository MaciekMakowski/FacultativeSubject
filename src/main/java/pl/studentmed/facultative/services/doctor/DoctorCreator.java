package pl.studentmed.facultative.services.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.doctor.Doctor;
import pl.studentmed.facultative.models.user_info.UserInfo;

@Component
@RequiredArgsConstructor
class DoctorCreator {

    private final DoctorRepository repository;

    public Doctor createDoctor(UserInfo userInfo) {
        var doctor = new Doctor(userInfo);
        return repository.saveAndFlush(doctor);
    }
}
