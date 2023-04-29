package pl.studentmed.facultative.services.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.studentmed.facultative.models.appointment.Appointment;
import pl.studentmed.facultative.models.appointment.AppointmentDate;
import pl.studentmed.facultative.models.doctor.Doctor;

interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByAppointmentDateAndDoctor(AppointmentDate appointmentDate, Doctor doctor);

}
