package pl.studentmed.facultative.services.addresses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.exceptions.EntityNotFoundException;
import pl.studentmed.facultative.models.address.Address;

@Component
@RequiredArgsConstructor
class AddressReader {

    private final AddressRepository repository;

    public Address getAddressById(Long addressId) {
        return repository.findById(addressId)
                .orElseThrow(
                        () -> new EntityNotFoundException("address", "Address with id: " + addressId + " doesn't exists.")
                );
    }

}
