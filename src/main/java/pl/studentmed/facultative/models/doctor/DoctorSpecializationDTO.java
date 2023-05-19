package pl.studentmed.facultative.models.doctor;

import lombok.Builder;

public record DoctorSpecializationDTO(Long doctorId,
                                      String specialization,
                                      String description,
                                      String photo,
                                      String firstName,
                                      String lastName) {

    @Builder public DoctorSpecializationDTO {}

}
