package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.AddressDao;
import com.amr.project.model.entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl extends ReadWriteDaoImpl<Address, Long> implements AddressDao {
    @Override
    public Address findById(Long id) {
        return em.find(Address.class, id);
    }
}
