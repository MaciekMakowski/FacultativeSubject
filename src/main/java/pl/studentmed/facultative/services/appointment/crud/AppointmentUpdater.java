package pl.studentmed.facultative.services.appointment.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.exceptions.AppointmentAlreadyDoneException;
import pl.studentmed.facultative.exceptions.AppointmentCantBeCancelledException;
import pl.studentmed.facultative.models.appointment.Appointment;
import pl.studentmed.facultative.models.appointment.AppointmentDate;
import pl.studentmed.facultative.models.appointment.AppointmentEditDTO;
import pl.studentmed.facultative.models.appointment.AppointmentStatus;
import pl.studentmed.facultative.models.user_info.Role;
import pl.studentmed.facultative.models.user_info.UserInfo;

import java.time.LocalDateTime;

import static pl.studentmed.facultative.models.StudentMedDateUtils.DAY_MONTH_YEAR_TIME;

@Component
@RequiredArgsConstructor
class AppointmentUpdater {

    private final AppointmentRepository repository;

    public Appointment editAppointment(Appointment appointmentToEdit, AppointmentEditDTO dto, UserInfo userWhichIsEditing) {
        if (dto.newStatus() != null) {
            changeAppointmentStatus(appointmentToEdit, dto.newStatus(), userWhichIsEditing);
        }
        var modifiedAt = LocalDateTime.now();
        var modifiedAtFormatted = modifiedAt.format(DAY_MONTH_YEAR_TIME);
        appointmentToEdit.setModifiedAt(modifiedAtFormatted);
        return repository.saveAndFlush(appointmentToEdit);
    }

    public Appointment finnishAppointment(Appointment appointment, String recommendations, String diagnosis) {
        if (recommendations != null) {
            appointment.setRecommendations(recommendations);
        }
        if (diagnosis != null) {
            appointment.setDiagnosis(diagnosis);
        }
        var newStatus = AppointmentStatus.getAppointmentStatus("done");
        if (appointment.getStatus().equals(newStatus)) {
            throw new AppointmentAlreadyDoneException("appointmentStatus", "This appointment already has the status done.");
        }
        appointment.setStatus(newStatus);
        return repository.saveAndFlush(appointment);
    }

    private void changeAppointmentDate(Appointment appointmentToEdit, LocalDateTime givenAppointmentDate) {
        var newAppointmentDate = new AppointmentDate(givenAppointmentDate);
        appointmentToEdit.setAppointmentDate(newAppointmentDate);
        appointmentToEdit.setStatus(AppointmentStatus.RESCHEDULED);
    }

    private void changeAppointmentStatus(Appointment appointmentToEdit, String givenStatus, UserInfo userWhichIsEditing) {
        var newStatus = AppointmentStatus.getAppointmentStatus(givenStatus);
        if (AppointmentStatus.isCanceled(newStatus)) {
            if (!appointmentToEdit.canBeCanceled() && userWhichIsEditing.getRole() != Role.RECEPTION) {
                throw new AppointmentCantBeCancelledException("appointment",
                        "Appointment can't be cancelled if there is less than 24 hours left.");
            }
        }
        appointmentToEdit.setStatus(newStatus);
    }

}
