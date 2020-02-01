package anresdev.restaurante.api.service;

import anresdev.restaurante.api.model.ItemPedido;
import anresdev.restaurante.api.repository.ItemPedidoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemPedidoService {

    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository){
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Transactional
    public List<ItemPedido> BuscaTodos(){
        return itemPedidoRepository.findAll();
    }

    @Transactional
    public void SalvaPedido(@NotNull ItemPedido ItemPedido){
        itemPedidoRepository.save(ItemPedido);
    }

    @Transactional
    public void ExcluiPedido(@NotNull ItemPedido ItemPedido){
        itemPedidoRepository.delete(ItemPedido);
    }

    @Transactional
    public void BuscaPedidoPorId(@NotNull Integer itemPedidoId){
        itemPedidoRepository.findById(itemPedidoId);
    }

}
