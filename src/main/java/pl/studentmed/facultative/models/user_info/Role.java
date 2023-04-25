package pl.studentmed.facultative.models.user_info;

import pl.studentmed.facultative.exceptions.InvalidRoleException;

import java.util.stream.Stream;

public enum Role {

    PATIENT("patient"),
    DOCTOR("doctor"),
    RECEPTION("reception");

    public String value;

    Role(String role) {
        this.value = role;
    }

    public static Role getRole(String givenValue) {
        return Stream.of(values())
                .filter(role -> role.value.equals(givenValue))
                .findFirst()
                .orElseThrow(() -> new InvalidRoleException(String.format("Role must be one of '%s', '%s', or '%s'",
                        PATIENT.value, DOCTOR.value, RECEPTION.value)));
    }

}
