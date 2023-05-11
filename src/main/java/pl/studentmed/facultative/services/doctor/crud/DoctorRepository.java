package pl.studentmed.facultative.services.doctor.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.studentmed.facultative.models.doctor.Doctor;
import pl.studentmed.facultative.models.doctor.DoctorSpecializationDTO;
import pl.studentmed.facultative.models.doctor.Specialization;

import java.util.List;

interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("""
            select new pl.studentmed.facultative.models.doctor.DoctorSpecializationDTO
            (
                doc.id,
                concat(doc.userInfo.firstName, ' ', doc.userInfo.lastName),
                cast(doc.specialization as string) 
            )
            from Doctor doc
            where doc.specialization = :specialization
           """)
    List<DoctorSpecializationDTO> getDoctorsBySpecialization(@Param("specialization") Specialization specialization);

}
