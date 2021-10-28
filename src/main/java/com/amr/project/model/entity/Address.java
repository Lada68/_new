package com.amr.project.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cityIndex;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    private Country country;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    private City city;

    private String street;
    private String house;

    @ManyToMany
    @JoinTable(
            name = "users_addresses",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    @Transient
    private Long countryId;

    @Transient
    private Long cityId;

    public Address(String cityIndex, Country country, City city, String street, String house) {
        this.cityIndex = cityIndex;
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
    }

    public Address() {

    }
}
