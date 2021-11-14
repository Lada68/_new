package com.amr.project.service.abstracts;

import com.amr.project.model.entity.City;

public interface CityService extends ReadWriteService<City, Long>{
    void addNewCity(City city);

    City getByName(String name);

    City findByName(String name);

    City findById(Long id);

}
