package pl.studentmed.facultative.models.patient;

import lombok.Builder;
import pl.studentmed.facultative.models.address.AddressUpdateDTO;
import pl.studentmed.facultative.models.user_info.UserInfoUpdateDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record PatientUpdateDTO(@NotNull
                               Long patientId,
                               @Valid
                               UserInfoUpdateDTO userInfoUpdateDTO,
                               @Size(max = 255, message = "Text size cannot exceed 255 characters.")
                               String allergies,
                               @Size(max = 255, message = "Text size cannot exceed 255 characters.")
                               String medicines,
                               AddressUpdateDTO addressUpdateDTO
) {

    @Builder public PatientUpdateDTO {}
}
