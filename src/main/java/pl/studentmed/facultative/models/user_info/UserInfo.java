package pl.studentmed.facultative.models.user_info;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.studentmed.facultative.models.BasicEntity;
import pl.studentmed.facultative.models.addresses.Address;

import java.time.LocalDate;
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
    private String pesel;
    private LocalDate birthdate;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Builder
    public UserInfo(String firstName, String lastName, String email, String password,
                    LocalDate birthdate, String phoneNumber, String pesel, Role role,
                    Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        this.address = address;
    }

}
