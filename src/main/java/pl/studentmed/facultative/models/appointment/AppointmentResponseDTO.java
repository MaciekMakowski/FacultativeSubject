package pl.studentmed.facultative.models.appointment;

import lombok.Builder;

public record AppointmentResponseDTO(Long appointmentId,
                                     String patientName,
                                     String doctorName,
                                     String appointmentDate,
                                     String patientSymptoms,
                                     AppointmentStatus appointmentStatus,
                                     String doctorRecommendations,
                                     String createdAt,
                                     String modifiedAt) {

    @Builder public AppointmentResponseDTO {}

}
