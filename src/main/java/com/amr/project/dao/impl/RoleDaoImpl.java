package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.RoleDao;
import com.amr.project.model.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends ReadWriteDaoImpl<Role, Long> implements RoleDao {

    public Role getRoleById(Long id) {
        return em.createQuery("select u from Role u where u.id=:id", Role.class)
                .setParameter("id", id).getSingleResult();
    }
    @Override
    public Role getRoleByName(String name) {
        return em.createQuery("select u from Role u where u.name=:name", Role.class)
                .setParameter("name", name).getSingleResult();
    }
}
