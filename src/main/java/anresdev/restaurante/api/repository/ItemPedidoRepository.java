package anresdev.restaurante.api.repository;

import anresdev.restaurante.api.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

    //public List<ItemPedido> findAllByPedidoIs(Integer pedidoId)

    @Query(value ="SELECT * FROM item_pedido WHERE pedido_id = :pedidoId", nativeQuery = true)
    List<ItemPedido> findAllByPedidoId(@Param("pedidoId") Integer pedidoId);
}
