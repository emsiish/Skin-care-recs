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
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @ManyToMany(mappedBy = "tags", cascade = CascadeType.MERGE)
    List<User> users;

    @ManyToMany(mappedBy = "tags", cascade = CascadeType.MERGE)
    List<Product> products;
}