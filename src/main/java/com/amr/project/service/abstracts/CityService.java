package com.amr.project.service.abstracts;

import com.amr.project.model.entity.City;

public interface CityService {
    void addNewCity(City city);

    City findByName(String name);
}
