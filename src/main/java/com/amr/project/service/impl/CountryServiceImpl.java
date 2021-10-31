package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.CountryDao;
import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.model.entity.Country;
import com.amr.project.service.abstracts.CountryService;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl extends ReadWriteServiceImpl<Country, Long> implements CountryService {

    private final CountryDao countryDao;

    public CountryServiceImpl(ReadWriteDao<Country, Long> readWriteDao, CountryDao countryDao) {
        super(readWriteDao);
        this.countryDao = countryDao;
    }

    @Override
    public void addNewCountry(Country country) {
        if (countryDao.getByName(country.getName())) {
            countryDao.persist(country);
        }
    }
    @Override
    public Country findByName(String name){
       return countryDao.findByName(name);
    }
}