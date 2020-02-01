package anresdev.restaurante.api.service;

import anresdev.restaurante.api.model.ItemCardapio;
import anresdev.restaurante.api.repository.ItemCardapioRepository;
import anresdev.restaurante.api.repository.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.jetbrains.annotations.*;

import java.util.List;
import java.util.Optional;

@Service
public class ItemCardapioService {

    private final ItemCardapioRepository itemCardapioRepository;

    @Autowired
    public ItemCardapioService(ItemCardapioRepository itemCardapioRepository){
        this.itemCardapioRepository = itemCardapioRepository;
    }

    @Transactional
    public List<ItemCardapio> BuscaTodos(){
        return itemCardapioRepository.findAll();
    }

    @Transactional
    public ItemCardapio buscaPor(@NotNull Integer id) {
        return itemCardapioRepository.findById(id).get();
    }

    @Transactional
    public List<ItemCardapio> BuscaDisponiveis(){
        return itemCardapioRepository.findItemCardapioByDisponivelIsTrue();
    }

    @Transactional
    public List<ItemCardapio> BuscaEncerrados(){
        return itemCardapioRepository.findAll();
    }

    @Transactional
    public Optional<ItemCardapio> BuscaItemDoCardapioPorId(@NotNull Integer itemId){
        return itemCardapioRepository.findById(itemId);
    }

    @Transactional
    public ItemCardapio CadastraItemNoCardapio(@NotNull ItemCardapio item){
        return itemCardapioRepository.save(item);
    }

    @Transactional
    public void RemoveItemDoCardapio(@NotNull ItemCardapio item){
        itemCardapioRepository.delete(item);
    }

    @Transactional
    public void RemoveItemDoCardapio(@NotNull Integer itemId){
        itemCardapioRepository.deleteById(itemId);
    }

    @Transactional
    public ItemCardapio atualiza(Integer id, ItemCardapio itemCardapio) {

        ItemCardapio itemCardapioSalvo = this.buscaPor(id);
        BeanUtils.copyProperties(itemCardapio, itemCardapioSalvo, "id");

        return itemCardapioSalvo;
    }
}
