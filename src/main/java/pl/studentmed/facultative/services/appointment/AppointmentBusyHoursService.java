package pl.studentmed.facultative.services.appointment;

import org.springframework.stereotype.Service;
import pl.studentmed.facultative.models.appointment.AppointmentsHoursDTO;
import pl.studentmed.facultative.models.appointment.AvaliableHoursDTO;

import java.util.ArrayList;
import java.util.List;

import static pl.studentmed.facultative.models.StudentMedDateUtils.SELECTABLE_HOURS;

@Service
class AppointmentBusyHoursService {

    public List<AppointmentsHoursDTO> getAppointmentHours(List<String> busyHoursAtDay) {
        var fullHours = extractFullHours(SELECTABLE_HOURS);
        return map(fullHours, busyHoursAtDay);
    }

    private List<AppointmentsHoursDTO> map(List<String> fullHours, List<String> busyHoursAtDay) {
        var result = new ArrayList<AppointmentsHoursDTO>();

        for (String fullHour: fullHours) {
            var hoursAndMinutes = extractHoursAndMinutesForFullHour(fullHour, SELECTABLE_HOURS);
            var dto = new AppointmentsHoursDTO(fullHour, new ArrayList<>());
            for (String hourAndMinute: hoursAndMinutes) {
                if(busyHoursAtDay.contains(hourAndMinute)) {
                    dto.hours().add(new AvaliableHoursDTO(hourAndMinute, false));
                }
                else {
                    dto.hours().add(new AvaliableHoursDTO(hourAndMinute, true));
                }
            }
            result.add(dto);
        }
        return result;
    }

    private List<String> extractFullHours(List<String> hours) {
        return hours.stream()
                .map(hour -> hour.substring(0, 2))
                .distinct()
                .toList();
    }

    private List<String> extractHoursAndMinutesForFullHour(String fullHour, List<String> hoursAndMinutes) {
        return hoursAndMinutes.stream()
                .filter(hoursAndMinute -> {
                    String hour = hoursAndMinute.substring(0, 2);
                    return hour.equals(fullHour);
                })
                .toList();
    }

//    var hoursAndMinutesForFullHour = new ArrayList<String>();
//        for (String hourAndMinute: hoursAndMinutes) {
//        String hour = hourAndMinute.substring(0, 3);
//        if (hour.equals(fullHour)) {
//            hoursAndMinutesForFullHour.add(hourAndMinute);
//        }
//    }
//        return hoursAndMinutesForFullHour;
}
