package com.amr.project.service.abstracts;

import com.amr.project.model.entity.Country;

public interface CountryService extends ReadWriteService<Country, Long>{
    void addNewCountry(Country country);

    Country findByName(String name);

    Country findById(Long id);

}
