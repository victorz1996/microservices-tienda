package com.example.tienda.contoller;

import com.example.tienda.entity.Categorias;
import com.example.tienda.entity.Games;
import com.example.tienda.service.GamesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GamesController {
    private final GamesService gamesService;

    public GamesController(GamesService gamesService) {
        this.gamesService = gamesService;
    }

    @GetMapping
    public ResponseEntity<List<Games>> listGames(@RequestParam(name = "categoriaId", required = false) Long categoriaId) {
        List<Games> games;
        if (null == categoriaId) {
            games = gamesService.listAllGames();
            if (games.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        } else {
            games = gamesService.findByCategoria(Categorias.builder().categoria_id(categoriaId).build());
            if (games.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Games> getGame(@PathVariable("id") Long id) {
        Games game = gamesService.getGame(id);
        if (null == game) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }

    @PostMapping
    public ResponseEntity<Games> createGame(@RequestBody Games game) {
        Games gameCreate = gamesService.createGame(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(gameCreate);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Games> updateGame(@PathVariable("id") Long id, @RequestBody Games game) {
        game.setGames_id(id);
        Games gameDB = gamesService.updateGame(game);
        if (gameDB == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gameDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Games> deleteGame(@PathVariable("id") Long id) {
        Games gameDelete = gamesService.deleteGame(id);
        if (gameDelete == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gameDelete);
    }

    @GetMapping(value = "/{id}/stock")
    public ResponseEntity<Games> updateStockGame(@PathVariable Long id, @RequestParam(name = "cantidad", required = true) Double cantidad) {
        Games game = gamesService.updateStock(id, cantidad);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }
}
