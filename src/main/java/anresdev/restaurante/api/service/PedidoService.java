package anresdev.restaurante.api.service;

import anresdev.restaurante.api.model.ItemPedido;
import anresdev.restaurante.api.model.Pedido;
import anresdev.restaurante.api.repository.PedidoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService (PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public List<Pedido> BuscaTodos(){
        return pedidoRepository.findAll();
    }

    @Transactional
    public Pedido buscaPor(@NotNull Integer id) {
        return pedidoRepository.findById(id).get();
    }

    @Transactional
    public Pedido CadastraPedido(@NotNull Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void ExcluiPedido(@NotNull Pedido pedido){
        pedidoRepository.delete(pedido);
    }

    @Transactional
    public void ExcluiPedido(@NotNull Integer pedidoId){
        pedidoRepository.deleteById(pedidoId);
    }

    @Transactional
    public Optional<Pedido> BuscaPedidoPorId(@NotNull Integer pedidoId){
        return pedidoRepository.findById(pedidoId);
    }

    @Transactional
    public Pedido atualiza(Integer id, Pedido pedido) {

        Pedido pedidoSalvo = this.buscaPor(id);
        BeanUtils.copyProperties(pedido, pedidoSalvo, "id");

        return pedidoSalvo;
    }
}
