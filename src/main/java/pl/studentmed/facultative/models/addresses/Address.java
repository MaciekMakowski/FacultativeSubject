package pl.studentmed.facultative.models.addresses;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.studentmed.facultative.models.BasicEntity;

@Entity
@Table(name = "addresses")
@Setter
@Getter
@NoArgsConstructor
public class Address extends BasicEntity {

    private String city;
    private ZipCode zipCode;
    private String street;

    public Address(String city, ZipCode zipCode, String street) {
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
    }

}
