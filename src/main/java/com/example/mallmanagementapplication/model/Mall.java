package com.example.mallmanagementapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "malls")
public class Mall implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** NAME: litere, cifre, spații */
    @NotBlank(message = "Mall name cannot be blank")
    @Size(min = 2, max = 60, message = "Mall name must be between 2 and 60 characters")
    @Pattern(
            regexp = "^[A-Za-zÀ-ž0-9\\s]+$",
            message = "Mall name may contain only letters, digits and spaces"
    )
    private String name;

    /** CITY: litere, cifre, spații */
    @NotBlank(message = "City cannot be blank")
    @Size(min = 2, max = 50, message = "City must be between 2 and 50 characters")
    @Pattern(
            regexp = "^[A-Za-zÀ-ž0-9\\s]+$",
            message = "City may contain only letters, digits and spaces"
    )
    private String city;

    /** COUNTRY: litere, cifre, spații */
    @NotBlank(message = "Country cannot be blank")
    @Size(min = 2, max = 50, message = "Country must be between 2 and 50 characters")
    @Pattern(
            regexp = "^[A-Za-zÀ-ž0-9\\s]+$",
            message = "Country may contain only letters, digits and spaces"
    )
    private String country;

    @OneToMany(mappedBy = "mall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Floor> floors = new ArrayList<>();

    public Mall() {}

    public Mall(String name, String city, String country) {
        this.name = name;
        this.city = city;
        this.country = country;
    }

    @Override
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public List<Floor> getFloors() { return floors; }
    public void setFloors(List<Floor> floors) { this.floors = floors; }
}
