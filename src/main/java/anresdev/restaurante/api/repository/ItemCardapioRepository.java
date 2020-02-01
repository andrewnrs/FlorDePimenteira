package anresdev.restaurante.api.repository;

import anresdev.restaurante.api.model.ItemCardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Integer> {

    public List<ItemCardapio> findItemCardapioByDisponivelIsTrue();
}
