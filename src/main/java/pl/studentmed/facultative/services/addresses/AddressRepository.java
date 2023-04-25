package pl.studentmed.facultative.services.addresses;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.studentmed.facultative.models.addresses.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {



}
