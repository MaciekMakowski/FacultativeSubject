package pl.studentmed.facultative.services.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.studentmed.facultative.models.doctor.Doctor;
import pl.studentmed.facultative.models.doctor.DoctorSpecializationDTO;

import java.util.List;

interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("""
            select new pl.studentmed.facultative.models.doctor.DoctorSpecializationDTO
            (
                doc.id,
                concat(doc.userInfo.firstName, ' ', doc.userInfo.lastName),
                doc.specialization
            )
            from Doctor doc
            where doc.specialization = :specialization
           """)
    List<DoctorSpecializationDTO> getDoctorsBySpecialization(@Param("specialization") String specialization);

}
