package com.amr.project.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@ToString
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @ToString.Exclude
    private Country country;

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public City() {

    }
    public City(String name){
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        City city = (City) o;

        return Objects.equals(id, city.id);
    }

    @Override
    public int hashCode() {
        return 39525063;
    }
}
