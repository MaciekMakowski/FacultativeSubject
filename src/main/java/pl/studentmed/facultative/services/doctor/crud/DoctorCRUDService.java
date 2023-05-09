package pl.studentmed.facultative.services.doctor.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.studentmed.facultative.models.doctor.Doctor;
import pl.studentmed.facultative.models.doctor.DoctorSpecializationDTO;
import pl.studentmed.facultative.models.user_info.UserInfo;

import java.util.List;

import static pl.studentmed.facultative.models.DTOMapper.toDTO;

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

    public List<DoctorSpecializationDTO> getDoctorsBySpecialization(String specialization) {
        return reader.getDoctorsBySpecialization(specialization);
    }

    public DoctorSpecializationDTO changeDoctorSpecialization(Long doctorId, String specialization) {
        var doctor = reader.getDoctorById(doctorId);
        var updatedDoctor = updater.changeDoctorSpecialization(doctor, specialization);
        return toDTO(updatedDoctor);
    }

}
