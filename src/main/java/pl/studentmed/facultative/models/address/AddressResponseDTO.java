package pl.studentmed.facultative.models.address;

import lombok.Builder;

public record AddressResponseDTO(Long addressId,
                                 String city,
                                 String street,
                                 String zipCode) {

    @Builder public AddressResponseDTO {}

}
