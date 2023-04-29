package pl.studentmed.facultative.models.user_info;

import pl.studentmed.facultative.exceptions.InvalidRoleException;

import java.util.stream.Stream;

public enum Role {

    PATIENT("patient"),
    DOCTOR("doctor"),
    RECEPTION("reception");

    public final String value;

    Role(String role) {
        this.value = role;
    }

    public static Role getRole(String givenValue) {
        return Stream.of(values())
                .filter(role -> role.value.equals(givenValue))
                .findFirst()
                .orElseThrow(InvalidRoleException::new);
    }

    public static boolean isPatient(String role) {
        return role.equals(PATIENT.value);
    }

    public static boolean isDoctor(String role) {
        return role.equals(DOCTOR.value);
    }

    public static boolean isReception(String role) {
        return role.equals(RECEPTION.value);
    }

}
