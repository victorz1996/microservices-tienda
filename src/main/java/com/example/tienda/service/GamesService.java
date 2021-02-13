package com.example.tienda.service;

import com.example.tienda.entity.Categorias;
import com.example.tienda.entity.Games;

import java.util.List;

public interface GamesService {
    public List<Games> listAllGames();
    public Games getGame(Long id);
    public Games createGame(Games game);
    public Games updateGame(Games game);
    public Games deleteGame(Long id);
    public List<Games> findByCategoria(Categorias categoria);
    public Games updateStock(Long id, Double cantidad);
}
