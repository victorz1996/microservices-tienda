package com.example.tienda.repository;

import com.example.tienda.entity.Categorias;
import com.example.tienda.entity.Games;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GamesRespository extends JpaRepository<Games, Long> {
    public List<Games> findByCategoria(Categorias categoria);
}
