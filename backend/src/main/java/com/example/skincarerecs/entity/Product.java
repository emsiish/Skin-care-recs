package com.example.skincarerecs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.validation.constraints.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Brand cannot be blank")
    private String brand;

    @NotBlank(message = "Type cannot be blank")
    private String type;

    @NotBlank(message = "Image cannot be blank")
    private String image;

    @Column(name = "price", columnDefinition = "DOUBLE")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    //@Digits(integer = 6, fraction = 2, message = "Invalid price format")
    private Double price;

    @OneToMany(mappedBy = "product")
    private List<ProductRating> productRating;

    @ManyToMany
    @JoinTable(
            name = "product_tag",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;
}
