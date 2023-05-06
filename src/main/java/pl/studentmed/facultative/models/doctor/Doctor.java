package pl.studentmed.facultative.models.doctor;

import javax.persistence.*;
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
@Table(name = "doctors")
public class Doctor extends BasicEntity {

    @OneToOne
    @JoinColumn(name = "user_infos_id")
    private UserInfo userInfo;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    private String specialization;

    public Doctor(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}
