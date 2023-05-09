package pl.studentmed.facultative.services.addresses;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.address.Address;

@Component
@RequiredArgsConstructor
public class AddressCRUDService {

    private final AddressCreator creator;

    public Address createEmptyAddress() {
        return creator.createEmptyAddress();
    }

}
