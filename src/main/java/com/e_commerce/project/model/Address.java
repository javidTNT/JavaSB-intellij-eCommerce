package com.e_commerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 5, message = "Address must be at least 5 characters")
    private String street;

    @NotBlank
    @Size(min = 5, message = "City must be at least 5 characters")
    private String city;

    @NotBlank
    @Size(min = 2, message = "country must be at least 2 characters")
    private String country;

    @NotBlank
    @Size(min = 2, message = "state must be at least 2 characters")
    private String state;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<User> users = new ArrayList<>();

    public Address(String street, String city, String country, String state) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.state = state;
    }
}
