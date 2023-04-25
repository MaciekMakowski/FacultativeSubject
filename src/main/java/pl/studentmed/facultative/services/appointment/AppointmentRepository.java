package pl.studentmed.facultative.services.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.studentmed.facultative.models.appointment.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {



}
