package com.amr.project.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    @JoinColumn(name = "country_id")
    private Country country;

    @Transient
    private Long countryId;

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public City() {

    }
}
