package com.example.tienda;

import com.example.tienda.entity.Categorias;
import com.example.tienda.entity.Games;
import com.example.tienda.repository.GamesRepository;
import com.example.tienda.service.GamesService;
import com.example.tienda.service.GamesServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class GameServiceMockTest {

    @Mock
    private GamesRepository gamesRepository;

    private GamesService gamesService;

    @BeforeEach
    public void  setup(){
        MockitoAnnotations.openMocks(this);
        gamesService = new GamesServiceImpl(gamesRepository);
        Games fifa20 = Games.builder()
                .games_id(1L)
                .game_nombre("FIFA 20")
                .estado("ACTIVO")
                .cantidad(Double.parseDouble("10"))
                .precio(Double.parseDouble("10.50"))
                .descripcion("Juego de mundo abierto")
                .categoria(Categorias.builder().categoria_id(1L).build())
                .build();

        Mockito.when(gamesRepository.findById(1L))
                .thenReturn(Optional.of(fifa20));
        Mockito.when(gamesRepository.save(fifa20)).thenReturn(fifa20);
    }

    @Test
    public void whenValidGetID_ThenReturnGame(){
        Games found = gamesService.getGame(1L);
        Assertions.assertThat(found.getGame_nombre()).isEqualTo("FIFA 20");
    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock(){
        Games newStock = gamesService.updateStock(1L, Double.parseDouble("8"));
        Assertions.assertThat(newStock.getCantidad()).isEqualTo(18);
    }
}
