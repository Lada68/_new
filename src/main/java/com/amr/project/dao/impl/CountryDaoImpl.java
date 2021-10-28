package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.CountryDao;
import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Country;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDaoImpl extends ReadWriteDaoImpl<Country,Long> implements CountryDao {

    @Override
    public Country findById(Long id){
        return em.find(Country.class, id);
    }
}
