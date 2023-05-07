package pl.studentmed.facultative.models.doctor;

import lombok.Builder;

public record DoctorSpecializationDTO(Long doctorId, String doctorName, String specialization) {

    @Builder public DoctorSpecializationDTO {}

}
