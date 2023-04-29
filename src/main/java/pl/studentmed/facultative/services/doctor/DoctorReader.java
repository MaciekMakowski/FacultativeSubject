package pl.studentmed.facultative.services.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.exceptions.EntityNotFoundException;
import pl.studentmed.facultative.models.doctor.Doctor;

@Component
@RequiredArgsConstructor
class DoctorReader {

    private final DoctorRepository repository;

    public Doctor getDoctorById(Long doctorId) {
        return repository.findById(doctorId)
                .orElseThrow(
                        () -> new EntityNotFoundException("doctor", "Doctor with id: " + doctorId + " doesn't exists.")
                );
    }

}
