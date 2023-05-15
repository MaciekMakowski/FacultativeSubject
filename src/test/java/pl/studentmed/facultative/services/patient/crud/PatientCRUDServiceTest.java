package pl.studentmed.facultative.services.patient.crud;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.studentmed.facultative.models.patient.Patient;
import pl.studentmed.facultative.models.patient.PatientUpdateDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.studentmed.facultative.services.patient.PatientTestUtils.createUserInfo;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PatientCRUDServiceTest {

    private final InMemoryPatientRepository inMemoryPatientRepository = new InMemoryPatientRepository();
    private final PatientReader reader = new PatientReader(inMemoryPatientRepository);
    private final PatientCreator creator = new PatientCreator(inMemoryPatientRepository);
    private final PatientUpdater updater = new PatientUpdater(inMemoryPatientRepository);

    private final PatientCRUDService patientCRUDService = new PatientCRUDService(reader, creator, updater);
    private Patient firstPatient;

    @BeforeAll
    void inserRecordsToDB() {
        Patient patient1 = new Patient(createUserInfo(1L));
        Patient patient2 = new Patient(createUserInfo(2L));
        firstPatient = inMemoryPatientRepository.saveAndFlush(patient1);
        inMemoryPatientRepository.saveAndFlush(patient2);
    }

    @Test
    void shouldReturnPatient() {
        // when
        var foundPatient = patientCRUDService.getPatientById(firstPatient.getId());
        // then
        assertThat(foundPatient).isNotNull();
        assertThat(foundPatient.getId()).isEqualTo(firstPatient.getId());
    }

    @Test
    void shouldReturnPatientsList() {
        // given
        var offset = 0;
        var limit = 5;
        // when
        var patients = patientCRUDService.getAllPatients(offset, limit);
        // then
        assertThat(patients).hasSizeGreaterThan(0);
    }

    @Test
    void shouldCreatePatient() {
        // given
        var newUserInfo = createUserInfo(3L);
        // when
        var createdPatient = patientCRUDService.createPatient(newUserInfo);
        // then
        var offset = 0;
        var limit = 5;
        assertThat(patientCRUDService.getAllPatients(offset, limit)).contains(createdPatient);
    }

    @Test
    void shouldUpdatePatientAllergies_and_Medicines() {
        // given
        PatientUpdateDTO dto = PatientUpdateDTO.builder()
                .patientId(1L)
                .userInfoUpdateDTO(null)
                .allergies("grass allergy")
                .medicines("rutinoscorbin")
                .addressUpdateDTO(null)
                .build();
        // when
        patientCRUDService.updatePatient(dto);
        // then
        assertThat(firstPatient.getAllergies()).isEqualTo("grass allergy");
        assertThat(firstPatient.getMedicines()).isEqualTo("rutinoscorbin");
    }

}
