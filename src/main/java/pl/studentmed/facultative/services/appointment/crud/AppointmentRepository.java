package pl.studentmed.facultative.services.appointment.crud;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.studentmed.facultative.models.appointment.Appointment;
import pl.studentmed.facultative.models.appointment.AppointmentBusyHoursDTO;
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
                concat(p.userInfo.firstName, ' ', p.userInfo.lastName),
                concat(d.userInfo.firstName, ' ', d.userInfo.lastName),
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
                concat(p.userInfo.firstName, ' ', p.userInfo.lastName),
                concat(d.userInfo.firstName, ' ', d.userInfo.lastName),
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
                concat(p.userInfo.firstName, ' ', p.userInfo.lastName),
                concat(d.userInfo.firstName, ' ', d.userInfo.lastName),
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
            order by app.appointmentDate.date asc
            """
    )
    List<AppointmentResponseDTO> getAppointmentsByDoctorIdAndAppointmentDateLike(@Param("doctorId") Long doctorId,
                                                                                 @Param("appointmentDate") String appointmentDate,
                                                                                 Pageable pageable);
    @Query(
            """
            select new pl.studentmed.facultative.models.appointment.AppointmentResponseDTO
                (
                app.id,
                concat(p.userInfo.firstName, ' ', p.userInfo.lastName),
                concat(d.userInfo.firstName, ' ', d.userInfo.lastName),
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
            where p.id = :patientId
            and app.appointmentDate.date like :appointmentDate%
            order by app.appointmentDate.date asc
            """
    )
    List<AppointmentResponseDTO> getAppointmentsByPatientIdAndAppointmentDateLike(@Param("patientId") Long patientId,
                                                                                  @Param("appointmentDate") String wantedDate,
                                                                                  Pageable pageable);

    @Query("""
           select new pl.studentmed.facultative.models.appointment.AppointmentResponseDTO
                (
                app.id,
                concat(p.userInfo.firstName, ' ', p.userInfo.lastName),
                concat(d.userInfo.firstName, ' ', d.userInfo.lastName),
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
            where p.id = :patientId
            order by app.appointmentDate.date asc
           """)
    List<AppointmentResponseDTO> getAppointmentsByPatientId(@Param("patientId") Long patientId, Pageable pageable);


    @Query("""
            select new pl.studentmed.facultative.models.appointment.AppointmentBusyHoursDTO
                (
                substring(app.appointmentDate.date, 12)
                )
            from Appointment app
            where app.appointmentDate.date like :appointmentDate%
           """)
    List<AppointmentBusyHoursDTO> getBusyAppointmentsHoursByGivenDate(@Param("appointmentDate") String appointmentDate);

    @Query("""
            select new pl.studentmed.facultative.models.appointment.AppointmentResponseDTO
                (
                app.id,
                concat(p.userInfo.firstName, ' ', p.userInfo.lastName),
                concat(d.userInfo.firstName, ' ', d.userInfo.lastName),
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
            where p.id = :patientId
            and app.appointmentDate.date between :startDate AND :endDate
            order by app.appointmentDate.date asc
           """)
    List<AppointmentResponseDTO> getAppointmentsByPatientIdAndDateBetween(@Param("patientId") Long patientId,
                                                                          @Param("startDate") String startDate,
                                                                          @Param("endDate") String endDate,
                                                                          PageRequest pageable);

}
