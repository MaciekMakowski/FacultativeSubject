package pl.studentmed.facultative.models.user_info;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.studentmed.facultative.models.BasicEntity;
import pl.studentmed.facultative.models.addresses.Address;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_infos")
public class UserInfo extends BasicEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime birthdate;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}
