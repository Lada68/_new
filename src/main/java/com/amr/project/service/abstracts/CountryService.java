package com.amr.project.service.abstracts;

import com.amr.project.model.entity.Country;
import com.amr.project.model.entity.User;

public interface CountryService extends ReadWriteService<Country, Long>{
    Country findById(Long id);
}
