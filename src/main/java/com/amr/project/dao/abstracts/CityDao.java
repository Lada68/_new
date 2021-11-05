package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.City;

public interface CityDao extends ReadWriteDao<City, Long> {

    City findByName(String name);

    boolean getByName(String name);

    City findById(Long id);
}
