package pl.studentmed.facultative.services.patient.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PatientConfig {

    private final JPAPatientRepository jpaPatientRepository;

    @Bean
    public PatientRepository patientRepository() {
        return new PatientRepository(jpaPatientRepository);
    }

    @Bean
    public PatientReader patientReader(PatientRepository patientRepository) {
        return new PatientReader(patientRepository);
    }

}
