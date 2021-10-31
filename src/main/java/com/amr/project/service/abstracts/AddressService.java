package com.amr.project.service.abstracts;

import com.amr.project.model.entity.Address;

public interface AddressService {
    void addNewAddress(Address address);

    Address getByAddress(Address address);

    Address findById(Long id);
}
