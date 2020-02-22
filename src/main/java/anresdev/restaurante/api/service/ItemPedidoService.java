package anresdev.restaurante.api.service;

import anresdev.restaurante.api.model.ItemCardapio;
import anresdev.restaurante.api.model.ItemPedido;
import anresdev.restaurante.api.repository.ItemPedidoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public ItemPedido buscaPor(@NotNull Integer id) {
        return itemPedidoRepository.findById(id).get();
    }

    @Transactional
    public Optional<ItemPedido> buscaItemPedidoPor(@NotNull Integer id) {
        return itemPedidoRepository.findById(id);
    }

    @Transactional
    public List<ItemPedido> BuscaItensDoPedido(@NotNull Integer idPedido) {
        return itemPedidoRepository.findAllByPedidoId(idPedido);
    }

    @Transactional
    public ItemPedido CadastraItem(@NotNull ItemPedido itemPedido){
        return itemPedidoRepository.save(itemPedido);
    }

    @Transactional
    public void ExcluiItemPedido(@NotNull ItemPedido itemPedido){
        itemPedidoRepository.delete(itemPedido);
    }

    @Transactional
    public void ExcluiItemPedido(@NotNull Integer itemPedidoId){
        itemPedidoRepository.deleteById(itemPedidoId);
    }

    @Transactional
    public void BuscaItemPedidoPorId(@NotNull Integer itemPedidoId){
        itemPedidoRepository.findById(itemPedidoId);
    }

    @Transactional
    public ItemPedido atualiza(Integer id, ItemPedido itemPedido) {

        ItemPedido itemPedidoSalvo = this.buscaPor(id);
        BeanUtils.copyProperties(itemPedido, itemPedidoSalvo, "id");

        return itemPedidoSalvo;
    }
}
