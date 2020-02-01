package anresdev.restaurante.api.service;

import anresdev.restaurante.api.model.ItemCardapio;
import anresdev.restaurante.api.repository.ItemCardapioRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemCardapioService {

    private ItemCardapioRepository itemCardapioRepository;

    @Autowired
    public ItemCardapioService(ItemCardapioRepository itemCardapioRepository){
        this.itemCardapioRepository = itemCardapioRepository;
    }


    @Transactional
    public List<ItemCardapio> BuscaTodos(){
        return itemCardapioRepository.findAll();
    }

    @Transactional
    public void SalvaPedido(@NotNull ItemCardapio itemCardapio){
        itemCardapioRepository.save(itemCardapio);
    }

    @Transactional
    public void ExcluiPedido(@NotNull ItemCardapio itemCardapio){
        itemCardapioRepository.delete(itemCardapio);
    }

    @Transactional
    public void BuscaPedidoPorId(@NotNull Integer itemCardapioId){
        itemCardapioRepository.findById(itemCardapioId);
    }


}
