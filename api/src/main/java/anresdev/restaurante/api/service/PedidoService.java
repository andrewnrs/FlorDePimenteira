package anresdev.restaurante.api.service;

import anresdev.restaurante.api.model.Pedido;
import anresdev.restaurante.api.repository.PedidoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PedidoService {

    PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService (PedidoRepository pedidoRepository ){
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public List<Pedido> BuscaTodos(){
        return pedidoRepository.findAll();
    }

    @Transactional
    public void SalvaPedido(@NotNull Pedido pedido){
        pedidoRepository.save(pedido);
    }

    @Transactional
    public void ExcluiPedido(@NotNull Pedido pedido){
        pedidoRepository.delete(pedido);
    }

    @Transactional
    public void BuscaPedidoPorId(@NotNull Integer pedidoId){
        pedidoRepository.findById(pedidoId);
    }

}
