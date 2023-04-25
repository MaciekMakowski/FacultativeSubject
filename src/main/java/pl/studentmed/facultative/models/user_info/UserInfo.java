package pl.studentmed.facultative.models.user_info;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.studentmed.facultative.models.BasicEntity;
import pl.studentmed.facultative.models.addresses.Address;

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
    private String birthDate;
    private Role role;
    private String phoneNumber;
    private String createdAt;
    private String modifiedAt;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}
