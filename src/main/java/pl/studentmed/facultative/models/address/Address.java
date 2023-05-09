package pl.studentmed.facultative.models.address;

import javax.persistence.Entity;
import javax.persistence.Table;
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

    private City city;
    private ZipCode zipCode;
    private Street street;

    public Address(City city, ZipCode zipCode, Street street) {
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
    }

}
