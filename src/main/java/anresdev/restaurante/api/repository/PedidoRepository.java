package anresdev.restaurante.api.repository;

import anresdev.restaurante.api.model.ItemPedido;
import anresdev.restaurante.api.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query(value ="SELECT * FROM pedido WHERE estado = :estado", nativeQuery = true)
    List<Pedido> findAllByEstadoAberto(@Param("estado") String estado);
}
