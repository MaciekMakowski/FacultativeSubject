package pl.studentmed.facultative.models.doctor;

import pl.studentmed.facultative.exceptions.InvalidSpecializationException;

import java.util.stream.Stream;

public enum Specialization {

    INTERNIST("internist"),
    GASTROENTEROLOGIST("gastroenterologist"),
    OPHTHALMOLOGIST("ophthalmologist"),
    PULMONOLOGIST("pulmonologist");

    public final String value;

    Specialization(String specialization) {
        this.value = specialization;
    }

    public static Specialization getSpecialization(String givenValue) {
        return Stream.of(values())
                .filter(specialization -> specialization.value.equals(givenValue))
                .findFirst()
                .orElseThrow(InvalidSpecializationException::new);
    }

}
