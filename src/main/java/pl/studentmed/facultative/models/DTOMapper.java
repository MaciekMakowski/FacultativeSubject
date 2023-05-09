package pl.studentmed.facultative.models;

import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.address.Address;
import pl.studentmed.facultative.models.address.AddressResponseDTO;
import pl.studentmed.facultative.models.appointment.Appointment;
import pl.studentmed.facultative.models.appointment.AppointmentResponseDTO;
import pl.studentmed.facultative.models.doctor.Doctor;
import pl.studentmed.facultative.models.doctor.DoctorSpecializationDTO;
import pl.studentmed.facultative.models.doctor.DoctorUserInfoDTO;
import pl.studentmed.facultative.models.patient.Patient;
import pl.studentmed.facultative.models.patient.PatientResponseDTO;
import pl.studentmed.facultative.models.user_info.UserInfo;
import pl.studentmed.facultative.models.user_info.UserInfoResponseDTO;

import static pl.studentmed.facultative.models.StudentMedDateUtils.DAY_MONTH_YEAR;
import static pl.studentmed.facultative.models.StudentMedDateUtils.DAY_MONTH_YEAR_TIME;

@Component
public class DTOMapper {

    public static PatientResponseDTO toDTO(Patient patient, UserInfo userInfo, Address address) {
            return new PatientResponseDTO(
                    patient.getId(),
                    patient.getAllergies(),
                    patient.getMedicines(),
                    toDTO(userInfo),
                    toDTO(address)
            );
    }

    public static DoctorUserInfoDTO toDTO(Doctor doctor, UserInfo userInfo, Address address) {
        return new DoctorUserInfoDTO(
                doctor.getId(),
                doctor.getSpecialization(),
                toDTO(userInfo),
                toDTO(address)
        );
    }

    public static UserInfoResponseDTO toDTO(UserInfo userInfo) {
        return UserInfoResponseDTO.builder()
                .userInfoId(userInfo.getId())
                .firstName(userInfo.getFirstName())
                .lastName(userInfo.getLastName())
                .email(userInfo.getEmail())
                .userRole(userInfo.getRole().value)
                .birthdate(userInfo.getBirthdate().format(DAY_MONTH_YEAR))
                .createdAt(userInfo.getCreatedAt().format(DAY_MONTH_YEAR_TIME))
                .modifiedAt(userInfo.getModifiedAt().format(DAY_MONTH_YEAR_TIME))
                .build();
    }

    public static AppointmentResponseDTO toDTO(Appointment appointment) {
        var patient = appointment.getPatient().getUserInfo();
        var doctor = appointment.getDoctor().getUserInfo();
        return AppointmentResponseDTO.builder()
                .appointmentId(appointment.getId())
                .patientName(combineStrings(patient.getFirstName(), patient.getLastName()))
                .doctorName(combineStrings(doctor.getFirstName(), doctor.getLastName()))
                .appointmentDate(appointment.getAppointmentDate().date)
                .appointmentStatus(appointment.getStatus())
                .patientSymptoms(appointment.getPatientSymptoms())
                .doctorRecommendations(appointment.getRecommendations())
                .createdAt(appointment.getCreatedAt())
                .modifiedAt(appointment.getModifiedAt())
                .build();
    }

    public static DoctorSpecializationDTO toDTO(Doctor doctor) {
        return DoctorSpecializationDTO.builder()
                .doctorId(doctor.getId())
                .doctorName(combineStrings(doctor.getUserInfo().getFirstName(), doctor.getUserInfo().getLastName()))
                .specialization(doctor.getSpecialization())
                .build();
    }

    public static AddressResponseDTO toDTO (Address address) {
        return AddressResponseDTO.builder()
                .addressId(address.getId())
                .city(address.getCity() != null ? address.getCity().value() : null)
                .street(address.getStreet() != null ? address.getStreet().value() : null)
                .zipCode(address.getZipCode() != null ? address.getZipCode().value() : null)
                .build();
    }

    private static String combineStrings(String first, String second) {
        var strBuilder = new StringBuilder();
        strBuilder.append(first);
        strBuilder.append(" ");
        strBuilder.append(second);
        return strBuilder.toString();
    }

}
