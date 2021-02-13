package com.example.tienda.service;

import com.example.tienda.entity.Categorias;
import com.example.tienda.entity.Games;
import com.example.tienda.repository.GamesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GamesServiceImpl implements GamesService{


    private final GamesRepository gamesRepository;

    @Override
    public List<Games> listAllGames() {
        return gamesRepository.findAll();
    }

    @Override
    public Games getGame(Long id) {
        return gamesRepository.findById(id).orElse(null);
    }

    @Override
    public Games createGame(Games game) {
        return gamesRepository.save(game);
    }

    @Override
    public Games updateGame(Games game) {
        Games gameDB = getGame(game.getGames_id());
        if (null == gameDB) {
            return null;
        }
        gameDB.setGame_nombre(game.getGame_nombre());
        gameDB.setDescripcion(game.getDescripcion());
        gameDB.setCategoria(game.getCategoria());
        gameDB.setPrecio(game.getPrecio());
        return gamesRepository.save(gameDB);
    }

    @Override
    public Games deleteGame(Long id) {
        Games gameDB = getGame(id);
        if (null == gameDB) {
            return null;
        }
        gameDB.setEstado("DELETED");
        return gamesRepository.save(gameDB);
    }

    @Override
    public List<Games> findByCategoria(Categorias categoria) {
        return gamesRepository.findByCategoria(categoria);
    }

    @Override
    public Games updateStock(Long id, Double cantidad) {
        Games gameDB = getGame(id);
        if (null == gameDB) {
            return null;
        }
        Double stock = gameDB.getCantidad() + cantidad;
        gameDB.setCantidad(stock);
        return gamesRepository.save(gameDB);
    }
}
