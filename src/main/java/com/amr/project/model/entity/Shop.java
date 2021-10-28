package com.amr.project.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country location;


    @OneToMany(mappedBy = "shop")
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<Review> reviews;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logo_id")
    private Image logo;

    private int count;
    private double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "shop")
    private List<Discount> discounts;

    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;
    private boolean isPretendedToBeDeleted = false;

}
