package com.amr.project.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@Getter
@Setter
//@NoArgsConstructor
@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country location;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;


    @OneToMany(mappedBy = "shop",
            cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    @ToString.Exclude
    private List<Review> reviews;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logo_id")
    @ToString.Exclude
    private Image logo;

    private int count;
    private double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "shop")
    @ToString.Exclude
    private List<Discount> discounts;

    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;
    private boolean isPretendedToBeDeleted = false;

    public Shop(String name, String email, String phone, String description, Country location, City city) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.description = description;
        this.location = location;
        this.city = city;
    }

    public Shop(Long id, String name, String email, String phone, String description, City city, Country location, List<Item> items, List<Review> reviews, Image logo, int count, double rating, User user, List<Discount> discounts, boolean isModerated, boolean isModerateAccept, String moderatedRejectReason, boolean isPretendedToBeDeleted) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.description = description;
        this.location = location;
        this.city = city;
        this.items = items;
        this.reviews = reviews;
        this.logo = logo;
        this.count = count;
        this.rating = rating;
        this.user = user;
        this.discounts = discounts;
        this.isModerated = isModerated;
        this.isModerateAccept = isModerateAccept;
        this.moderatedRejectReason = moderatedRejectReason;
        this.isPretendedToBeDeleted = isPretendedToBeDeleted;
    }
        public Shop() {
        this.location = new Country();
        this.city = new City();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Objects.equals(name, shop.name) && Objects.equals(email, shop.email) && Objects.equals(phone, shop.phone) && Objects.equals(description, shop.description) && Objects.equals(location, shop.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phone, description, location);
    }

}
