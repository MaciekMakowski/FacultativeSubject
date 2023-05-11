package pl.studentmed.facultative.services.addresses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.models.address.Address;
import pl.studentmed.facultative.models.address.AddressUpdateDTO;

@Component
@RequiredArgsConstructor
public class AddressCRUDService {

    private final AddressCreator creator;
    private final AddressReader reader;
    private final AddressUpdater updater;

    public Address createEmptyAddress() {
        return creator.createEmptyAddress();
    }

    public Address updateAddress(AddressUpdateDTO addressUpdateDTO) {
        var address = reader.getAddressById(addressUpdateDTO.addressId());
        return updater.updateAddress(
                address,
                addressUpdateDTO.city(),
                addressUpdateDTO.zipCode(),
                addressUpdateDTO.street()
        );
    }

}
