package pl.studentmed.facultative.services.doctor.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.exceptions.EntityNotFoundException;
import pl.studentmed.facultative.models.doctor.Doctor;
import pl.studentmed.facultative.models.doctor.DoctorSpecializationDTO;
import pl.studentmed.facultative.models.doctor.Specialization;

import java.util.List;

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

    public Doctor getDoctorByUserInfoId(Long userInfoId) {
        return repository.findDoctorByUserInfoId(userInfoId);
    }

    public List<DoctorSpecializationDTO> getAllDoctors() {
        return repository.getAllDoctors();
    }

    public List<DoctorSpecializationDTO> getDoctorsBySpecialization(String givenSpecialization) {
        var specialization = Specialization.getSpecialization(givenSpecialization);
        return repository.getDoctorsBySpecialization(specialization);
    }

    public List<Doctor> getAllDoctorsEntities() {
        return repository.findAll();
    }

}
