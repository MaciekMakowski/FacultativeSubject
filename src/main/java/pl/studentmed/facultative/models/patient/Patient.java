package pl.studentmed.facultative.models.patient;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.studentmed.facultative.models.BasicEntity;
import pl.studentmed.facultative.models.appointment.Appointment;
import pl.studentmed.facultative.models.user_info.UserInfo;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "patients")
public class Patient extends BasicEntity {

    @OneToOne
    @JoinColumn(name = "user_infos_id")
    UserInfo userInfo;

    @OneToMany(mappedBy = "patient")
    List<Appointment> appointments;

    public Patient(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
