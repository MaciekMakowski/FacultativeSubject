package pl.studentmed.facultative.services.doctor.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.studentmed.facultative.models.doctor.Doctor;
import pl.studentmed.facultative.models.doctor.DoctorSpecializationDTO;
import pl.studentmed.facultative.models.user_info.UserInfo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorCRUDService {

    private final DoctorReader reader;
    private final DoctorCreator creator;
    private final DoctorUpdater updater;

    public Doctor getDoctorById(Long doctorId) {
        return reader.getDoctorById(doctorId);
    }

    public Doctor createDoctor(UserInfo userInfo) {
        return creator.createDoctor(userInfo);
    }


    public List<DoctorSpecializationDTO> getAllDoctors() {
        return reader.getAllDoctors();
    }
    public List<DoctorSpecializationDTO> getDoctorsBySpecialization(String specialization) {
        return reader.getDoctorsBySpecialization(specialization);
    }

    public Doctor editDoctor(Long doctorId, String specialization, String description, String photo) {
        var doctor = reader.getDoctorById(doctorId);
        return updater.editDoctor(doctor, specialization, description, photo);
    }

}
