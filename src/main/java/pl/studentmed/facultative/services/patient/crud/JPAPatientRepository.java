package pl.studentmed.facultative.services.patient.crud;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.studentmed.facultative.models.patient.Patient;

import java.util.List;

interface JPAPatientRepository extends JpaRepository<Patient, Long> {

    @Query("""
            select distinct p
            from Patient p
            join fetch p.appointments app
            where app.doctor.id = :doctorId
           """)
    List<Patient> getAllPatientsByDoctorId(@Param("doctorId")Long doctorId, Pageable pageable);

    @Query(
            value = """
                    select *
                    from patients as p
                    join user_infos as ui
                    on p.user_infos_id = ui.id
                    where p.user_infos_id = ?1""",
            nativeQuery = true)
    Patient getPatientByUserInfoId(Long userInfoId);

}
