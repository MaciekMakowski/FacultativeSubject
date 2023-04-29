package pl.studentmed.facultative.services.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.doctor.Doctor;

@Component
@RequiredArgsConstructor
class DoctorFacade {

    private final DoctorCRUDService doctorCRUDService;

    public Doctor getDoctorById(Long doctorId) {
        return doctorCRUDService.getDoctorById(doctorId);
    }

}
