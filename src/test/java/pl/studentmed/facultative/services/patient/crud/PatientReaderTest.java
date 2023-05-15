package pl.studentmed.facultative.services.patient.crud;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.studentmed.facultative.models.patient.Patient;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.studentmed.facultative.services.patient.PatientTestUtils.createUserInfo;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PatientReaderTest {

    private final InMemoryPatientRepository inMemoryPatientRepository = new InMemoryPatientRepository();
    private final PatientReader patientReader = new PatientReader(inMemoryPatientRepository);
    private Patient firstPatient;

    @BeforeAll
    void insertRecordsToDB() {
        Patient patient1 = new Patient(createUserInfo(1L));
        Patient patient2 = new Patient(createUserInfo(2L));
        firstPatient = inMemoryPatientRepository.saveAndFlush(patient1);
        inMemoryPatientRepository.saveAndFlush(patient2);
    }

    @Test
    void shouldReturnPatient() {
        // when
        var result = patientReader.getPatientById(firstPatient.getId());
        // then
        assertThat(result).isInstanceOf(Patient.class);
    }

    @Test
    void shouldReturnPatientsList() {
        // when
        var offset = 0;
        var limit = 5;
        var result = patientReader.getAllPatients(offset, limit);
        // then
        assertThat(result).hasSize(2);
    }

}
