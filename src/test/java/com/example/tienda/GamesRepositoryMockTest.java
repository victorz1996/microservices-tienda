package com.example.tienda;

import com.example.tienda.entity.Categorias;
import com.example.tienda.entity.Games;
import com.example.tienda.repository.GamesRespository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GamesRepositoryMockTest {
    @Autowired
    private GamesRespository gamesRespository;
    @Test
    public void whenFindByCategoria_thenReturnListGames(){
        Games game01 = Games.builder()
                .game_nombre("GTA 4")
                .estado("ACTIVO")
                .cantidad(Double.parseDouble("10"))
                .precio(Double.parseDouble("10.50"))
                .descripcion("Juego de mundo abierto")
                .categoria(Categorias.builder().categoria_id(1L).build())
                .build();
        gamesRespository.save(game01);

        List<Games> founds = gamesRespository.findByCategoria(game01.getCategoria());

        Assertions.assertThat(founds.size()).isEqualTo(2);
    }
}
