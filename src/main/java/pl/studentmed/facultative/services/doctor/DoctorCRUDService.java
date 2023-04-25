package pl.studentmed.facultative.services.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.studentmed.facultative.models.doctor.Doctor;

@Service
@RequiredArgsConstructor
public class DoctorCRUDService {

    private final DoctorReader reader;

    public Doctor getDoctorById(Long doctorId) {
        return reader.getDoctorById(doctorId);
    }

}
