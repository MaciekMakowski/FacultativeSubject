package pl.studentmed.facultative.services.addresses;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.address.Address;

@Component
@RequiredArgsConstructor
class AddressCreator {

    private final AddressRepository repository;

    public Address createEmptyAddress() {
        var emptyAddress = new Address();
        return repository.saveAndFlush(emptyAddress);
    }
}
