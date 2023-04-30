package pl.studentmed.facultative.services.appointment;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.studentmed.facultative.models.appointment.Appointment;
import pl.studentmed.facultative.models.appointment.AppointmentDate;
import pl.studentmed.facultative.models.appointment.AppointmentResponseDTO;
import pl.studentmed.facultative.models.doctor.Doctor;

import java.util.List;
import java.util.Optional;

interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByAppointmentDateAndDoctor(AppointmentDate appointmentDate, Doctor doctor);

    @Query("""
            select new pl.studentmed.facultative.models.appointment.AppointmentResponseDTO
                (
                app.id,
                concat(p.userInfo.firstName, " ", p.userInfo.lastName),
                concat(d.userInfo.firstName, " ", d.userInfo.lastName),
                app.appointmentDate.date,
                app.patientSymptoms,
                app.status,
                app.recommendations,
                app.createdAt,
                app.modifiedAt
                )
            from Appointment app
            join fetch Patient p on app.patient = p
            join fetch Doctor d on app.doctor = d
            where app.id = :appointmentId
            """)
    Optional<AppointmentResponseDTO> getAppointmentById(@Param("appointmentId") Long appointmentId);

    @Query(
            """
            select new pl.studentmed.facultative.models.appointment.AppointmentResponseDTO
                (
                app.id,
                concat(p.userInfo.firstName, " ", p.userInfo.lastName),
                concat(d.userInfo.firstName, " ", d.userInfo.lastName),
                app.appointmentDate.date,
                app.patientSymptoms,
                app.status,
                app.recommendations,
                app.createdAt,
                app.modifiedAt
                )
            from Appointment app
            join fetch Patient p on app.patient = p
            join fetch Doctor d on app.doctor = d
            where d.id = :doctorId
            order by app.appointmentDate.date asc
            """
    )
    List<AppointmentResponseDTO> getAppointmentsByDoctorId(@Param("doctorId") Long doctorId, Pageable pageable);

    @Query(
            """
            select new pl.studentmed.facultative.models.appointment.AppointmentResponseDTO
                (
                app.id,
                concat(p.userInfo.firstName, " ", p.userInfo.lastName),
                concat(d.userInfo.firstName, " ", d.userInfo.lastName),
                app.appointmentDate.date,
                app.patientSymptoms,
                app.status,
                app.recommendations,
                app.createdAt,
                app.modifiedAt
                )
            from Appointment app
            join fetch Patient p on app.patient = p
            join fetch Doctor d on app.doctor = d
            where d.id = :doctorId
            and app.appointmentDate.date like :appointmentDate%
            """
    )
    List<AppointmentResponseDTO> getAppointmentsByDoctorIdAndAppointmentDateLike(@Param("doctorId") Long doctorId,
                                                                                 @Param("appointmentDate") String appointmentDate,
                                                                                 Pageable pageable);

}
