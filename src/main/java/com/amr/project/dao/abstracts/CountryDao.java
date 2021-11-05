package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Country;

public interface CountryDao extends ReadWriteDao<Country, Long> {

    Country findByName(String name);

    boolean getByName(String name);

    Country findById(Long id);
}
