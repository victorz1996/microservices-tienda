package com.example.tienda.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Games {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long games_id;
    private String game_nombre;
    @NotEmpty(message = "El campo no puede estar vacio")
    private Double cantidad;
    private Double precio;
    private String descripcion;
    private String estado;
    private String imagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Categorias categoria;
}
