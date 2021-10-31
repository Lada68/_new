package com.amr.project.service.abstracts;

import com.amr.project.model.entity.Country;

public interface CountryService {
    void addNewCountry(Country country);

    Country findByName(String name);
}
