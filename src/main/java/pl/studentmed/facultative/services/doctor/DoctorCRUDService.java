package pl.studentmed.facultative.services.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.studentmed.facultative.models.doctor.Doctor;
import pl.studentmed.facultative.models.user_info.UserInfo;

@Service
@RequiredArgsConstructor
public class DoctorCRUDService {

    private final DoctorReader reader;
    private final DoctorCreator creator;

    public Doctor getDoctorById(Long doctorId) {
        return reader.getDoctorById(doctorId);
    }

    public Doctor createDoctor(UserInfo userInfo) {
        return creator.createDoctor(userInfo);
    }
}
