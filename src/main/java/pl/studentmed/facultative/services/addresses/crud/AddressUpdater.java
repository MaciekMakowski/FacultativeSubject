package pl.studentmed.facultative.services.addresses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.address.Address;
import pl.studentmed.facultative.models.address.vo.City;
import pl.studentmed.facultative.models.address.vo.Street;
import pl.studentmed.facultative.models.address.vo.ZipCode;

@Component
@RequiredArgsConstructor
class AddressUpdater {

    private final AddressRepository repository;

    public Address updateAddress(Address address, String city, String zipCode, String street) {
        if (city != null) {
            address.setCity(new City(city));
        }
        if (zipCode != null) {
            address.setZipCode(new ZipCode(zipCode));
        }
        if (street != null) {
            address.setStreet(new Street(street));
        }
        return repository.saveAndFlush(address);
    }

}
